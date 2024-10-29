package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.domain.core.exception.NotFoundException;
import com.ryuqq.setof.storage.db.core.brand.BrandQueryRepository;
import com.ryuqq.setof.storage.db.core.brand.dto.BrandDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ryuqq.setof.domain.core.brand.BrandErrorConstants.BRAND_NOT_FOUND_ERROR_MSG;

@Component
public class BrandFinder implements BrandQueryService{

    private final BrandQueryRepository brandQueryRepository;

    public BrandFinder(BrandQueryRepository brandQueryRepository) {
        this.brandQueryRepository = brandQueryRepository;
    }


    public boolean brandExist(long brandId){
        return brandQueryRepository.fetchBrandExists(brandId);
    }

    public BrandRecord findBrand(long brandId){
        BrandDto brand = brandQueryRepository.fetchBrand(brandId)
                .orElseThrow(() -> new NotFoundException(BRAND_NOT_FOUND_ERROR_MSG + brandId));
        return BrandRecord.toBrandRecord(brand);
    }

    public List<BrandRecord> findBrands(BrandFilter brandFilter){
        List<BrandDto> brands = brandQueryRepository.fetchBrands(brandFilter.toStorageFilter());
        return brands.stream()
                .map(BrandRecord::toBrandRecord)
                .toList();
    }

    public long findBrandCount(BrandFilter brandFilter){
        return brandQueryRepository.fetchBrandCount(brandFilter.toStorageFilter());
    }


}
