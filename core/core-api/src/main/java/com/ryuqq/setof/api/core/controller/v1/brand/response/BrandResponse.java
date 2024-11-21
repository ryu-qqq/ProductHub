package com.ryuqq.setof.api.core.controller.v1.brand.response;

import com.ryuqq.setof.domain.core.brand.BrandRecord;

public record BrandResponse(
        long brandId,
        String brandName,
        String brandNameKr,
        String brandIconImageUrl,
        boolean displayYn
)  {
    public static BrandResponse of(BrandRecord brandRecord) {
        return new BrandResponse(brandRecord.id(), brandRecord.brandName(), brandRecord.brandNameKr(),
                brandRecord.brandIconImageUrl(), brandRecord.displayYn());
    }

}
