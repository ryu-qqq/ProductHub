package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.storage.db.core.brand.dto.BrandDto;
import com.ryuqq.setof.storage.db.index.brand.BrandDocument;

public record BrandRecord(
        long id,
        String brandName,
        String brandNameKr,
        String brandIconImageUrl,
        boolean displayYn
) {
    public static BrandRecord toBrandRecord(BrandDto brand) {
        return new BrandRecord(
                brand.getId(),
                brand.getBrandName(),
                brand.getBrandNameKr(),
                brand.getBrandIconImageUrl(),
                brand.isDisplayYn()
        );
    }

    public static BrandRecord toBrandRecord(BrandDocument brand) {
        return new BrandRecord(
                Long.parseLong(brand.getBrandId()),
                brand.getBrandName(),
                brand.getBrandNameKr(),
                brand.getBrandIconImageUrl(),
                brand.getDisplayYn()
        );
    }
}
