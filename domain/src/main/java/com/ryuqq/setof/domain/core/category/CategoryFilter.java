package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.core.CategoryType;
import com.ryuqq.setof.core.TargetGroup;

import java.util.List;

public record CategoryFilter(
        List<Long> categoryIds,
        TargetGroup targetGroup,
        CategoryType categoryType,
        Long cursorId,
        int pageSize
) {}
