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
        int pageSiz
) {
    public CategoryFilter toCategoryFilter(){
        return new CategoryFilter(categoryIds, targetGroup, categoryType, cursorId, pageSiz);
    }
}
