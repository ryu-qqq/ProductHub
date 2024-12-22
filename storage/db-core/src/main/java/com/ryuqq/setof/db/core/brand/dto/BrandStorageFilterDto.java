package com.ryuqq.setof.storage.db.core.brand.dto;

import java.util.List;

public record BrandStorageFilterDto (
        List<Long> brandIds,
        Long cursorId,
        String brandName,
        int pageSize
){}
