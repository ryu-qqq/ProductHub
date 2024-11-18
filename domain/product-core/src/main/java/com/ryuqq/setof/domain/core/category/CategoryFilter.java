package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.enums.core.CategoryType;
import com.ryuqq.setof.enums.core.TargetGroup;
import com.ryuqq.setof.storage.db.core.category.dto.CategoryStorageFilterDto;

import java.util.List;

public record CategoryFilter(
        List<Long> categoryIds,
        TargetGroup targetGroup,
        CategoryType categoryType,
        Long cursorId,
        int pageSize
) {
    public CategoryStorageFilterDto toStorageFilter() {
        return new CategoryStorageFilterDto(categoryIds, targetGroup, categoryType, cursorId, pageSize);
    }

}
