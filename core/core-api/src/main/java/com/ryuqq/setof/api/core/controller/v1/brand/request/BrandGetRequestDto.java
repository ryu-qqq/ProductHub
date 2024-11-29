package com.ryuqq.setof.api.core.controller.v1.brand.request;

import com.ryuqq.setof.domain.core.brand.BrandFilter;

import java.util.List;

public record BrandGetRequestDto(
        List<Long> brandIds,
        Long cursorId,
        String brandName,
        Integer pageSize
){

    public BrandFilter toBrandFilter(){
        int defaultSize = (pageSize == null || pageSize == 0) ? 20 : pageSize;
        List<Long> defaultIds = (brandIds == null || brandIds.isEmpty()) ? List.of() : brandIds;
        return new BrandFilter(defaultIds, cursorId, brandName, defaultSize);
    }

}
