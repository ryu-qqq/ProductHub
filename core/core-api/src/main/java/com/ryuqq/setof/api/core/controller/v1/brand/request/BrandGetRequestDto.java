package com.ryuqq.setof.api.core.controller.v1.brand.request;

import com.ryuqq.setof.domain.core.brand.BrandFilter;

import java.util.List;

public class BrandGetRequestDto{
    private List<Long> brandIds;
    private Long cursorId;
    private String brandName;
    private Integer pageSize;

    public BrandFilter toBrandFilter() {
        this.pageSize = (pageSize == null || pageSize == 0) ? 20 : pageSize;
        this.brandIds = (brandIds == null || brandIds.isEmpty()) ? List.of() : brandIds;
        return new BrandFilter(brandIds, cursorId, brandName, pageSize);
    }

    public BrandGetRequestDto(List<Long> brandIds, Long cursorId, String brandName, Integer pageSize) {
        this.brandIds = brandIds;
        this.cursorId = cursorId;
        this.brandName = brandName;
        this.pageSize = pageSize;
    }

    public Long getCursorId() {
        return cursorId;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
