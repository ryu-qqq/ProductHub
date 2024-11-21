package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.storage.db.core.brand.dto.BrandStorageFilterDto;
import com.ryuqq.setof.storage.db.index.brand.dto.BrandIndexFilterDto;

import java.util.List;

public record BrandFilter(
        List<Long> brandIds,
        Long cursorId,
        String brandName,
        int pageSize

) {
    public BrandStorageFilterDto toStorageFilter() {
        return new BrandStorageFilterDto(brandIds, cursorId, pageSize);
    }

    public BrandIndexFilterDto toIndexFilter() {
        List<String> brandIds = this.brandIds.stream().map(String::valueOf).toList();
        return new BrandIndexFilterDto(brandIds, brandName, cursorId, pageSize);
    }

}

