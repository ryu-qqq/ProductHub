package com.ryuqq.setof.storage.db.core.category.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.CategoryType;
import com.ryuqq.setof.enums.core.TargetGroup;

public class MappingCategoryDto {

    private final long siteId;
    private final String externalCategoryId;
    private final String externalExtraCategoryId;
    private final String description;
    private final long categoryId;
    private final String categoryName;
    private final TargetGroup targetGroup;
    private final CategoryType categoryType;

    @QueryProjection
    public MappingCategoryDto(long siteId, String externalCategoryId, String externalExtraCategoryId, String description, long categoryId, String categoryName, TargetGroup targetGroup, CategoryType categoryType) {
        this.siteId = siteId;
        this.externalCategoryId = externalCategoryId;
        this.externalExtraCategoryId = externalExtraCategoryId;
        this.description = description;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.targetGroup = targetGroup;
        this.categoryType = categoryType;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getExternalExtraCategoryId() {
        return externalExtraCategoryId;
    }

    public String getDescription() {
        return description;
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
