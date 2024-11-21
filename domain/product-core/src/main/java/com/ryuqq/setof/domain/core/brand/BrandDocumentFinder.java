package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.storage.db.index.brand.BrandDocument;
import com.ryuqq.setof.storage.db.index.brand.BrandDocumentQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandDocumentFinder implements BrandQueryService{

    private final BrandDocumentQueryRepository brandDocumentQueryRepository;

    public BrandDocumentFinder(BrandDocumentQueryRepository brandDocumentQueryRepository) {
        this.brandDocumentQueryRepository = brandDocumentQueryRepository;
    }

    @Override
    public boolean brandExist(long brandId) {
        return false;
    }

    @Override
    public BrandRecord findBrand(long brandId) {
        return null;
    }

    public List<BrandRecord> findBrands(BrandFilter brandFilter){
        List<BrandDocument> brandDocuments = brandDocumentQueryRepository.fetchBrands(brandFilter.toIndexFilter());
        return brandDocuments.stream()
                .map(BrandRecord::toBrandRecord)
                .toList();
    }

    public long findBrandCount(BrandFilter brandFilter){
        return brandDocumentQueryRepository.fetchBrandCount(brandFilter.toIndexFilter());
    }

}
