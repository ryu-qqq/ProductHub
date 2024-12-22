package com.ryuqq.setof.cache.core.brand;

public record BrandCacheDto(
        long id,
        String brandName,
        String brandNameKr,
        String brandIconImageUrl,
        boolean displayYn
) {
}
