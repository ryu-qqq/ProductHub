package com.ryuqq.setof.storage.db.index.brand;

import com.ryuqq.setof.storage.db.index.brand.dto.BrandIndexFilterDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BrandDocumentQueryFacade implements BrandDocumentQueryRepository{

    private final BrandDocumentEsQueryRepository brandDocumentEsQueryRepository;
    private final BrandEsRepository brandEsRepository;

    public BrandDocumentQueryFacade(BrandDocumentEsQueryRepository brandDocumentEsQueryRepository, BrandEsRepository brandEsRepository) {
        this.brandDocumentEsQueryRepository = brandDocumentEsQueryRepository;
        this.brandEsRepository = brandEsRepository;
    }


    @Override
    public boolean fetchBrandExists(long brandId) {
        Optional<BrandDocument> brandDocumentOpt = brandEsRepository.findById(String.valueOf(brandId));
        return brandDocumentOpt.isPresent();
    }

    @Override
    public Optional<BrandDocument> fetchBrand(long brandId) {
        return brandEsRepository.findById(String.valueOf(brandId));
    }

    @Override
    public List<BrandDocument> fetchBrands(BrandIndexFilterDto brandIndexFilterDto) {
        return brandDocumentEsQueryRepository.fetchBrands(brandIndexFilterDto);
    }

    @Override
    public long fetchBrandCount(BrandIndexFilterDto brandFilter) {
        return brandDocumentEsQueryRepository.fetchBrandCount(brandFilter);
    }

    @Override
    public List<BrandDocument> fetchBrands(List<String> brandNames) {
        return List.of();
    }
}
