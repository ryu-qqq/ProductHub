package com.ryuqq.setof.storage.db.core.category.dao;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.core.CategoryType;
import com.ryuqq.setof.core.TargetGroup;
import com.ryuqq.setof.domain.core.category.Category;

public class CategoryDao {

    private long id;

    private String categoryName;

    private int depth;

    private long parentCategoryId;

    private boolean displayYn;

    private TargetGroup targetGroup;

    private CategoryType categoryType;

    private String path;

    protected CategoryDao() {}

    @QueryProjection
    public CategoryDao(long id, String categoryName, int depth, long parentCategoryId, boolean displayYn, TargetGroup targetGroup, CategoryType categoryType, String path) {
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

    public int getDepth() {
        return depth;
    }

    public boolean isDisplayYn() {
        return displayYn;
    }

    public String getPath() {
        return path;
    }

    public Category toCategory(){
        return new Category(id, categoryName, depth, parentCategoryId, displayYn, targetGroup, categoryType, path);
    }

}
