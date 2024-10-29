package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.storage.db.core.brand.dto.BrandDto;

public record BrandRecord(
        long id,
        String brandName,
        String brandIconImageUrl,
        boolean displayYn
) {
    public static BrandRecord toBrandRecord(BrandDto brand) {
        return new BrandRecord(
                brand.getId(),
                brand.getBrandName(),
                brand.getBrandIconImageUrl(),
                brand.isDisplayYn()
        );
    }

}
