package com.ryuqq.setof.storage.db.core.category;

import com.ryuqq.setof.core.CategoryType;
import com.ryuqq.setof.core.TargetGroup;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

@Table(name = "CATEGORY")
@Entity
public class CategoryEntity extends BaseEntity {

    @Column(name = "CATEGORY_NAME", length = 50, nullable = false)
    private String categoryName;

    @Column(name = "DEPTH", nullable = false)
    private int depth;

    @Column(name = "PARENT_CATEGORY_ID", nullable = false)
    private long parentCategoryId;

    @Column(name = "DISPLAY_YN", length = 1)
    private boolean displayYn;

    @Column(name = "TARGET_GROUP", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private TargetGroup targetGroup;

    @Column(name = "CATEGORY_TYPE", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(name = "PATH", length = 50, nullable = false)
    private String path;

    protected CategoryEntity() {}

    public CategoryEntity(String categoryName, int depth, long parentCategoryId, boolean displayYn, TargetGroup targetGroup, CategoryType categoryType, String path) {
        this.categoryName = categoryName;
        this.depth = depth;
        this.parentCategoryId = parentCategoryId;
        this.displayYn = displayYn;
        this.targetGroup = targetGroup;
        this.categoryType = categoryType;
        this.path = path;
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
