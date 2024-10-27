package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.domain.core.exception.NotFoundException;
import com.ryuqq.setof.storage.db.core.brand.BrandQueryRepository;
import com.ryuqq.setof.storage.db.core.brand.dto.BrandDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ryuqq.setof.domain.core.brand.BrandErrorConstants.BRAND_NOT_FOUND_ERROR_MSG;

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
        BrandDto b = brandQueryRepository.fetchBrand(brandId)
                .orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND_ERROR_MSG + brandId));

        return new Brand(b.getId(), b.getBrandName(), b.getBrandIconImageUrl(), b.isDisplayYn());
    }

    public List<Brand> findBrands(BrandFilter brandFilter){
        List<BrandDto> brandDtos = brandQueryRepository.fetchBrands(brandFilter.toStorageFilter());
        return brandDtos.stream()
                .map(b ->
                        new Brand(b.getId(), b.getBrandName(),
                                b.getBrandIconImageUrl(), b.isDisplayYn())
                )
                .toList();
    }


    public long findBrandCount(BrandFilter brandFilter){
        return brandQueryRepository.fetchBrandCount(brandFilter.toStorageFilter());
    }

}
