package com.ryuqq.setof.storage.db.index.brand;

import com.ryuqq.setof.storage.db.index.brand.dto.BrandIndexFilterDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BrandDocumentHybridRepository implements BrandDocumentQueryRepository{

    private final BrandDocumentEsQueryRepository brandDocumentEsQueryRepository;
    private final BrandEsRepository brandEsRepository;

    public BrandDocumentHybridRepository(BrandDocumentEsQueryRepository brandDocumentEsQueryRepository, BrandEsRepository brandEsRepository) {
        this.brandDocumentEsQueryRepository = brandDocumentEsQueryRepository;
        this.brandEsRepository = brandEsRepository;
    }


    @Override
    public boolean existById(long brandId) {
        Optional<BrandDocument> brandDocumentOpt = brandEsRepository.findById(String.valueOf(brandId));
        return brandDocumentOpt.isPresent();
    }

    @Override
    public Optional<BrandDocument> fetchById(long brandId) {
        return brandEsRepository.findById(String.valueOf(brandId));
    }

    @Override
    public List<BrandDocument> fetchByFilter(BrandIndexFilterDto brandIndexFilterDto) {
        return brandDocumentEsQueryRepository.fetchBrands(brandIndexFilterDto);
    }

    @Override
    public long countByFilter(BrandIndexFilterDto brandFilter) {
        return brandDocumentEsQueryRepository.fetchBrandCount(brandFilter);
    }

    @Override
    public List<Long> fetchByBrandName(String brandName){
        return brandDocumentEsQueryRepository.fetchByBrandName(brandName).stream()
                .map(b -> Long.parseLong(b.getBrandId()))
                .toList();

    }

}
