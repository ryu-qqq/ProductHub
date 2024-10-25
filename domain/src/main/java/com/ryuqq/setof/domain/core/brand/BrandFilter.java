package com.ryuqq.setof.domain.core.brand;

import java.util.List;

public record BrandFilter(
        List<Long> brandIds,
        Long cursorId,
        int pageSize

) {}

