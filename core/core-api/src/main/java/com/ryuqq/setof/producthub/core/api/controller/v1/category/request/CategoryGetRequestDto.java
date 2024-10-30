package com.ryuqq.setof.producthub.core.api.controller.v1.category.request;

import com.ryuqq.setof.core.CategoryType;
import com.ryuqq.setof.core.TargetGroup;
import com.ryuqq.setof.domain.core.category.CategoryFilter;

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
