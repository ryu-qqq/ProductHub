package com.ryuqq.setof.producthub.core.api.controller.v1.brand.request;

import com.ryuqq.setof.domain.core.brand.BrandFilter;

import java.util.List;

public record BrandGetRequestDto(
        List<Long> brandIds,
        Long cursorId,
        int pageSize
) {
    public BrandFilter toBrandFilter() {
        return new BrandFilter(brandIds, cursorId, pageSize);
    }
}
