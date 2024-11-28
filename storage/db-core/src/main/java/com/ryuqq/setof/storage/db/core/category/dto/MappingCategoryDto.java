package com.ryuqq.setof.storage.db.core.category.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.CategoryType;
import com.ryuqq.setof.enums.core.TargetGroup;

public class MappingCategoryDto {

    private final String externalCategoryId;
    private final long categoryId;
    private final String categoryName;
    private final TargetGroup targetGroup;
    private final CategoryType categoryType;

    @QueryProjection
    public MappingCategoryDto(String externalCategoryId, long categoryId, String categoryName, TargetGroup targetGroup, CategoryType categoryType) {
        this.externalCategoryId = externalCategoryId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.targetGroup = targetGroup;
        this.categoryType = categoryType;
    }

    public String getExternalCategoryId() {
        return externalCategoryId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public TargetGroup getTargetGroup() {
        return targetGroup;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }
}
