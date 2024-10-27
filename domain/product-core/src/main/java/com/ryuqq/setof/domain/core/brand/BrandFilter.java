package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.storage.db.core.brand.dto.BrandStorageFilterDto;

import java.util.List;

public record BrandFilter(
        List<Long> brandIds,
        Long cursorId,
        int pageSize

) {
    public BrandStorageFilterDto toStorageFilter() {
        return new BrandStorageFilterDto(brandIds, cursorId, pageSize);
    }
}

