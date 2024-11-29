package com.ryuqq.setof.storage.db.index.brand;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandIndexingHybridRepository implements BrandDocumentIndexingRepository {

    private final BrandEsHighLevelRepository brandEsHighLevelRepository;
    private final BrandEsRepository brandEsRepository;

    public BrandIndexingHybridRepository(BrandEsHighLevelRepository brandEsHighLevelRepository, BrandEsRepository brandEsRepository) {
        this.brandEsHighLevelRepository = brandEsHighLevelRepository;
        this.brandEsRepository = brandEsRepository;
    }

    @Override
    public void insertBrandDocument(BrandDocument brandDocument) {
        brandEsRepository.save(brandDocument);
    }

    @Override
    public void insertAllBrandDocument(List<BrandDocument> brandDocuments) {
        brandEsHighLevelRepository.bulkInsert(brandDocuments);
    }

}
