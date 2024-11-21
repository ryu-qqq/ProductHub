package com.ryuqq.setof.storage.db.index.product;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupEsIndexingFacade implements ProductGroupDocumentIndexingService {

    private final ProductGroupEsRepository productGroupEsRepository;
    private final ProductGroupEsHighLevelRepository productGroupEsHighLevelRepository;

    public ProductGroupEsIndexingFacade(ProductGroupEsRepository productGroupEsRepository, ProductGroupEsHighLevelRepository productGroupEsHighLevelRepository) {
        this.productGroupEsRepository = productGroupEsRepository;
        this.productGroupEsHighLevelRepository = productGroupEsHighLevelRepository;
    }

    @Override
    public void insert(ProductGroupCommandContextDocument productGroupCommandContextDocument) {
        productGroupEsRepository.saveAll(List.of(productGroupCommandContextDocument));
    }

    @Override
    public void inserts(List<ProductGroupCommandContextDocument> productGroupCommandContextDocuments) {
        productGroupEsHighLevelRepository.bulkInsert(productGroupCommandContextDocuments);
    }

}
