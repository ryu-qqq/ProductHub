package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupQueryRepository;
import com.ryuqq.setof.storage.db.core.site.ExternalSiteQueryRepository;
import com.ryuqq.setof.storage.db.core.site.dto.SellerSiteRelationDto;
import com.ryuqq.setof.storage.db.core.site.dto.SitePolicyDto;
import com.ryuqq.setof.storage.db.index.product.ProductGroupCommandContextDocument;
import com.ryuqq.setof.storage.db.index.product.ProductGroupDocumentQueryRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProductGroupBatchReader implements ItemReader<List<ProductGroupProcessingData>> {

    private final ProductGroupDocumentQueryRepository productGroupDocumentQueryRepository;
    private final ProductGroupQueryRepository productGroupQueryRepository;
    private final ExternalSiteQueryRepository externalSiteQueryRepository;

    private Long cursorId = null;

    public ProductGroupBatchReader(ProductGroupDocumentQueryRepository productGroupDocumentQueryRepository, ProductGroupQueryRepository productGroupQueryRepository, ExternalSiteQueryRepository externalSiteQueryRepository) {
        this.productGroupDocumentQueryRepository = productGroupDocumentQueryRepository;
        this.productGroupQueryRepository = productGroupQueryRepository;
        this.externalSiteQueryRepository = externalSiteQueryRepository;
    }

    @Override
    public List<ProductGroupProcessingData> read() {
        List<Long> productGroupIds = productGroupQueryRepository.fetchProductGroupIds(ProductStatus.WAITING, cursorId, 20);

        if (productGroupIds == null || productGroupIds.isEmpty()) {
            return null;
        }

        cursorId = productGroupIds.getLast();


        List<ProductGroupCommandContextDocument> productGroupCommandContextDocuments = productGroupDocumentQueryRepository.fetchProductGroupCommandContextDocument(productGroupIds);
        List<Long> sellerIds = getSellerIds(productGroupCommandContextDocuments);

        Map<Long, List<SitePolicyDto>> sellerSiteMap = externalSiteQueryRepository.fetchSellerSiteRelation(null, sellerIds)
                .stream()
                .collect(Collectors.toMap(
                        SellerSiteRelationDto::getSellerId,
                        SellerSiteRelationDto::getSitePolicies
                ));

        return productGroupCommandContextDocuments.stream()
                .map(p -> {
                    Long sellerId = p.getProductGroupCommandDocument().sellerId();
                    List<SitePolicyDto> sitePolicies = sellerSiteMap.getOrDefault(sellerId, List.of());
                    return new ProductGroupProcessingData(p, sitePolicies);
                })
                .toList();
    }

    private List<Long> getSellerIds(List<ProductGroupCommandContextDocument> productGroupCommandContextDocuments) {
        return productGroupCommandContextDocuments.stream()
                .map(p -> p.getProductGroupCommandDocument().sellerId())
                .collect(Collectors.toSet())
                .stream()
                .toList();
    }


}
