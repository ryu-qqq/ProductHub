package com.ryuqq.setof.storage.db.cache.brand;

public record BrandCacheDto(
        long id,
        String brandName,
        String brandNameKr,
        String brandIconImageUrl,
        boolean displayYn
) {
}
