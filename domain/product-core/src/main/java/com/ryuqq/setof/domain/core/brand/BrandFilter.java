package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.storage.db.core.brand.dto.BrandStorageFilterDto;

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

    public boolean hasBrandName(){
        return brandName!= null && !brandName.isBlank();
    }

    public BrandFilter withBrandIds(List<Long> newBrandIds) {
        List<Long> combinedIds = new ArrayList<>(this.brandIds != null ? this.brandIds : List.of());
        combinedIds.addAll(newBrandIds);
        return new BrandFilter(combinedIds, cursorId, brandName, pageSize);
    }

    public BrandStorageFilterDto toStorageFilter() {
        return new BrandStorageFilterDto(brandIds, cursorId, pageSize);
    }

}

