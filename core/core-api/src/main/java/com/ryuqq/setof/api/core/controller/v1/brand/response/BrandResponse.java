package com.ryuqq.setof.api.core.controller.v1.brand.response;

import com.ryuqq.setof.domain.core.brand.Brand;

public record BrandResponse(
        long brandId,
        String brandName,
        String brandNameKr,
        String brandIconImageUrl,
        boolean displayYn
)  {
    public static BrandResponse of(Brand brand) {
        return new BrandResponse(brand.id(), brand.brandName(), brand.brandNameKr(),
                brand.brandIconImageUrl(), brand.displayYn());
    }

}
