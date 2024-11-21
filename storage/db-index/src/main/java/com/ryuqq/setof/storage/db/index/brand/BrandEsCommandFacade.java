package com.ryuqq.setof.storage.db.index.brand;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandEsCommandFacade implements BrandDocumentCommandService{

    private final BrandEsHighLevelRepository brandEsHighLevelRepository;
    private final BrandEsRepository brandEsRepository;

    public BrandEsCommandFacade(BrandEsHighLevelRepository brandEsHighLevelRepository, BrandEsRepository brandEsRepository) {
        this.brandEsHighLevelRepository = brandEsHighLevelRepository;
        this.brandEsRepository = brandEsRepository;
    }

    @Override
    public void insert(BrandDocument brandDocument) {
        brandEsRepository.save(brandDocument);
    }

    @Override
    public void inserts(List<BrandDocument> brandDocuments) {
        brandEsHighLevelRepository.bulkInsert(brandDocuments);
    }

}
