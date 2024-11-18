package com.ryuqq.setof.storage.db.index.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupEsCommandCommandService implements ProductGroupDocumentCommandService {

    private final ProductGroupEsRepository productGroupEsRepository;

    public ProductGroupEsCommandCommandService(ProductGroupEsRepository productGroupEsRepository) {
        this.productGroupEsRepository = productGroupEsRepository;
    }

    @Override
    public void insert(ProductGroupCommandContextDocument productGroupCommandContextDocument) {
        productGroupEsRepository.saveAll(List.of(productGroupCommandContextDocument));
    }

    @Override
    public void inserts(List<ProductGroupCommandContextDocument> productGroupCommandContextDocuments) {
        productGroupEsRepository.saveAll(productGroupCommandContextDocuments);
    }

}
