package com.ryuqq.setof.producthub.core.api.controller.v1.brand.request;

import com.ryuqq.setof.domain.core.brand.BrandFilter;

import java.util.List;

public class BrandGetRequestDto {
    private List<Long> brandIds;
    private Long cursorId;
    private int pageSize;

    protected BrandGetRequestDto() {}

    public BrandGetRequestDto(List<Long> brandIds, Long cursorId, int pageSize) {
        this.brandIds = brandIds;
        this.cursorId = cursorId;
        this.pageSize = pageSize;
    }

    public BrandFilter toBrandFilter() {
        return new BrandFilter(brandIds, cursorId, pageSize);
    }
}
