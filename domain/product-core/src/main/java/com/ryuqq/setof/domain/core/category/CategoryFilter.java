package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.enums.core.CategoryType;
import com.ryuqq.setof.enums.core.TargetGroup;
import com.ryuqq.setof.db.core.category.dto.CategoryStorageFilterDto;

import java.util.ArrayList;
import java.util.List;

public record CategoryFilter(
        List<Long> categoryIds,
        TargetGroup targetGroup,
        CategoryType categoryType,
        Long cursorId,
        int pageSize
) {

    public static CategoryFilter of(List<Long> categoryIds, TargetGroup targetGroup, CategoryType categoryType, Long cursorId, int pageSize) {
        return new CategoryFilter(categoryIds, targetGroup, categoryType, cursorId, pageSize);
    }


    public CategoryFilter withCategoryIds(List<Long> newCategoryIds) {
        List<Long> combinedIds = new ArrayList<>(this.categoryIds != null ? this.categoryIds : List.of());
        combinedIds.addAll(newCategoryIds);
        return new CategoryFilter(combinedIds, targetGroup, categoryType, cursorId, pageSize);
    }


    public CategoryStorageFilterDto toStorageFilter() {
        return new CategoryStorageFilterDto(categoryIds, targetGroup, categoryType, cursorId, pageSize);
    }

}
