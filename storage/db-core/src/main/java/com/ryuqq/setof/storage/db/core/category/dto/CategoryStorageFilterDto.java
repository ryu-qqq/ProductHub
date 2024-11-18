package com.ryuqq.setof.storage.db.core.category.dto;

import com.ryuqq.setof.enums.core.CategoryType;
import com.ryuqq.setof.enums.core.TargetGroup;

import java.util.List;

public record CategoryStorageFilterDto(
        List<Long> categoryIds,
        TargetGroup targetGroup,
        CategoryType categoryType,
        Long cursorId,
        int pageSize
) {}
