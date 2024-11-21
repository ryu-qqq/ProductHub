package com.ryuqq.setof.storage.db.index.brand.dto;

import java.util.List;

public record BrandIndexFilterDto(
        List<String> brandIds,
        String brandName,
        Long cursorId,
        int pageSize
) {}
