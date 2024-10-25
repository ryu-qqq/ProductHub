package com.ryuqq.setof.producthub.core.api.controller.v1.category.request;

import com.ryuqq.setof.core.CategoryType;
import com.ryuqq.setof.core.TargetGroup;
import com.ryuqq.setof.domain.core.category.CategoryFilter;

import java.util.List;

public class CategoryGetRequestDto {

    private List<Long> categoryIds;
    private TargetGroup targetGroup;
    private CategoryType categoryType;
    private Long cursorId;
    private int pageSiz;

    protected CategoryGetRequestDto() {}

    public CategoryGetRequestDto(List<Long> categoryIds, TargetGroup targetGroup, CategoryType categoryType, Long cursorId, int pageSiz) {
        this.categoryIds = categoryIds;
        this.targetGroup = targetGroup;
        this.categoryType = categoryType;
        this.cursorId = cursorId;
        this.pageSiz = pageSiz;
    }

    public CategoryFilter toCategoryFilter(){
        return new CategoryFilter(categoryIds, targetGroup, categoryType, cursorId, pageSiz);
    }
}
