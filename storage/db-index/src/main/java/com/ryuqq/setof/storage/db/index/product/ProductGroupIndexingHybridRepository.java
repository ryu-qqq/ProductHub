package com.ryuqq.setof.storage.db.index.product;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupIndexingHybridRepository implements ProductGroupDocumentIndexingRepository {

    private final ProductGroupEsRepository productGroupEsRepository;
    private final ProductGroupEsHighLevelRepository productGroupEsHighLevelRepository;

    public ProductGroupIndexingHybridRepository(ProductGroupEsRepository productGroupEsRepository, ProductGroupEsHighLevelRepository productGroupEsHighLevelRepository) {
        this.productGroupEsRepository = productGroupEsRepository;
        this.productGroupEsHighLevelRepository = productGroupEsHighLevelRepository;
    }

    @Override
    public void insertProductGroupCommandContext(ProductGroupCommandContextDocument productGroupCommandContextDocument) {
        productGroupEsRepository.save(productGroupCommandContextDocument);
    }

    @Override
    public void insertAllProductGroupCommandContext(List<ProductGroupCommandContextDocument> productGroupCommandContextDocuments) {
        productGroupEsHighLevelRepository.bulkInsert(productGroupCommandContextDocuments);
    }

}
