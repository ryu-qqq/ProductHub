package com.ryuqq.setof.api.core.controller.v1.brand.request;

import com.ryuqq.setof.domain.core.brand.BrandFilter;

import java.util.List;

public record BrandGetRequestDto(
        List<Long> brandIds,
        Long cursorId,
        Integer pageSize
) {
    public BrandFilter toBrandFilter() {
        int defaultSize = (pageSize == null || pageSize == 0) ? 20 : pageSize;
        return new BrandFilter(brandIds, cursorId, defaultSize);
    }
}
