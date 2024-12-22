package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.db.core.brand.dto.BrandStorageFilterDto;

import java.util.ArrayList;
import java.util.List;

public record BrandFilter(
        List<Long> brandIds,
        Long cursorId,
        String brandName,
        int pageSize

) {
    public static BrandFilter of(List<Long> brandIds, Long cursorId, String brandName, int pageSize) {
        return new BrandFilter(brandIds, cursorId, brandName, pageSize);
    }


    public BrandStorageFilterDto toStorageFilter() {
        return new BrandStorageFilterDto(brandIds, cursorId, brandName, pageSize);
    }

}

