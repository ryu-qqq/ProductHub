package com.ryuqq.setof.domain.core.brand;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandFinder {

    private final BrandQueryRepository brandQueryRepository;

    public BrandFinder(BrandQueryRepository brandQueryRepository) {
        this.brandQueryRepository = brandQueryRepository;
    }

    public boolean brandExist(long brandId){
        return brandQueryRepository.fetchBrandExists(brandId);
    }

    public Brand findBrand(long brandId){
        return brandQueryRepository.fetchBrand(brandId)
                .orElseThrow();
    }

    public List<Brand> findBrands(BrandFilter brandFilter){
        return brandQueryRepository.fetchBrands(brandFilter);
    }


    public long findBrandCount(BrandFilter brandFilter){
        return brandQueryRepository.fetchBrandCount(brandFilter);
    }


}
