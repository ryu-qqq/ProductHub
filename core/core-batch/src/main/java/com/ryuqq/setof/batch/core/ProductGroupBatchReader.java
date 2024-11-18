package com.ryuqq.setof.batch.core;

import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupQueryRepository;
import com.ryuqq.setof.storage.db.index.product.ProductGroupCommandContextDocument;
import com.ryuqq.setof.storage.db.index.product.ProductGroupDocumentQueryRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupBatchReader implements ItemReader<List<ProductGroupCommandContextDocument>> {

    private final ProductGroupDocumentQueryRepository productGroupDocumentQueryRepository;
    private final ProductGroupQueryRepository productGroupQueryRepository;

    public ProductGroupBatchReader(ProductGroupDocumentQueryRepository productGroupDocumentQueryRepository, ProductGroupQueryRepository productGroupQueryRepository) {
        this.productGroupDocumentQueryRepository = productGroupDocumentQueryRepository;
        this.productGroupQueryRepository = productGroupQueryRepository;
    }

    @Override
    public List<ProductGroupCommandContextDocument> read() {
        List<Long> productGroupIds = productGroupQueryRepository.fetchProductGroupIds(ProductStatus.WAITING, 20);

        if (productGroupIds == null || productGroupIds.isEmpty()) {
            return null;
        }

        return productGroupDocumentQueryRepository.fetchProductGroupCommandContextDocument(productGroupIds);
    }
}
