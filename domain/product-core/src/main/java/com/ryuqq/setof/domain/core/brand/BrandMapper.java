package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.storage.db.cache.brand.BrandCacheDto;
import com.ryuqq.setof.storage.db.core.brand.dto.BrandDto;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public Brand toDomain(BrandDto brandDto) {
        return new Brand(
                brandDto.getId(),
                brandDto.getBrandName(),
                brandDto.getBrandNameKr(),
                brandDto.getBrandIconImageUrl(),
                brandDto.isDisplayYn()
        );
    }

    public Brand toDomain(BrandCacheDto cacheDto) {
        return new Brand(
                cacheDto.id(),
                cacheDto.brandName(),
                cacheDto.brandNameKr(),
                cacheDto.brandIconImageUrl(),
                cacheDto.displayYn()
        );
    }

    public BrandCacheDto toCacheDto(Brand brand) {
        return new BrandCacheDto(
                brand.id(),
                brand.brandName(),
                brand.brandNameKr(),
                brand.brandIconImageUrl(),
                brand.displayYn()
        );
    }

}
