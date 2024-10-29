package com.ryuqq.setof.producthub.core.api.controller.v1.brand.response;

import com.ryuqq.setof.domain.core.brand.BrandRecord;

public record BrandResponse(
        long id,
        String brandName,
        String brandIconImageUrl,
        boolean displayYn
)  {
    public static BrandResponse of(BrandRecord brandRecord) {
        return new BrandResponse(brandRecord.id(), brandRecord.brandName(),
                brandRecord.brandIconImageUrl(), brandRecord.displayYn());
    }

}
