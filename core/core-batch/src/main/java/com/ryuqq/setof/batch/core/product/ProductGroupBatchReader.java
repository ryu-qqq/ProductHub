package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupQueryRepository;
import com.ryuqq.setof.storage.db.index.product.ProductGroupCommandContextDocument;
import com.ryuqq.setof.storage.db.index.product.ProductGroupDocumentQueryRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupBatchReader implements ItemReader<List<ProductGroupProcessingData>> {

    private final ProductGroupDocumentQueryRepository productGroupDocumentQueryRepository;
    private final ProductGroupQueryRepository productGroupQueryRepository;

    private Long cursorId = null;

    public ProductGroupBatchReader(ProductGroupDocumentQueryRepository productGroupDocumentQueryRepository, ProductGroupQueryRepository productGroupQueryRepository) {
        this.productGroupDocumentQueryRepository = productGroupDocumentQueryRepository;
        this.productGroupQueryRepository = productGroupQueryRepository;
    }


    @Override
    public List<ProductGroupProcessingData> read() {
        List<Long> productGroupIds = productGroupQueryRepository.fetchIdsByStatusAndCursor(ProductStatus.WAITING, cursorId, 100);

        if (productGroupIds == null || productGroupIds.isEmpty()) {
            return null;
        }

        cursorId = productGroupIds.getLast();
        List<ProductGroupCommandContextDocument> productGroupCommandContextDocuments = productGroupDocumentQueryRepository.fetchByIds(productGroupIds);

        return productGroupCommandContextDocuments.stream()
                .map(ProductGroupProcessingData::new)
                .toList();
    }
}
