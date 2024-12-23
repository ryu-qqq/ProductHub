package com.ryuqq.setof.api.core.controller.v1.category.request;

import com.ryuqq.setof.domain.core.category.CategoryFilter;
import com.ryuqq.setof.enums.core.CategoryType;
import com.ryuqq.setof.enums.core.TargetGroup;

import java.util.List;

public record CategoryGetRequestDto(
        List<Long> categoryIds,
        TargetGroup targetGroup,
        CategoryType categoryType,
        Long cursorId,
        Integer pageSize
) {
    public CategoryFilter toCategoryFilter(){
        int defaultSize = (pageSize == null || pageSize == 0) ? 20 : pageSize;
        return new CategoryFilter(categoryIds, targetGroup, categoryType, cursorId, defaultSize);
    }
}
