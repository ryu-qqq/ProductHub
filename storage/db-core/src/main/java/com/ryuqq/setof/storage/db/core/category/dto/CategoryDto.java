package com.ryuqq.setof.storage.db.core.category.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.core.CategoryType;
import com.ryuqq.setof.core.TargetGroup;

public class CategoryDto {

    private long id;

    private String categoryName;

    private int depth;

    private long parentCategoryId;

    private boolean displayYn;

    private TargetGroup targetGroup;

    private CategoryType categoryType;

    private String path;

    protected CategoryDto() {}

    @QueryProjection
    public CategoryDto(long id, String categoryName, int depth, long parentCategoryId, boolean displayYn, TargetGroup targetGroup, CategoryType categoryType, String path) {
        this.id = id;
        this.categoryName = categoryName;
        this.depth = depth;
        this.parentCategoryId = parentCategoryId;
        this.displayYn = displayYn;
        this.targetGroup = targetGroup;
        this.categoryType = categoryType;
        this.path = path;
    }

    public long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getDepth() {
        return depth;
    }

    public long getParentCategoryId() {
        return parentCategoryId;
    }

    public boolean isDisplayYn() {
        return displayYn;
    }

    public TargetGroup getTargetGroup() {
        return targetGroup;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public String getPath() {
        return path;
    }
}
