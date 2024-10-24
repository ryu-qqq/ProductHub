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

    @Column(name = "CATEGORY_DEPTH", nullable = false)
    private int categoryDepth;

    @Column(name = "PARENT_CATEGORY_ID", nullable = false)
    private long parentCategoryId;

    @Column(name = "DISPLAY_YN", length = 1)
    @Enumerated(EnumType.STRING)
    private boolean displayYn;

    @Column(name = "TARGET_GROUP", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private TargetGroup targetGroup;

    @Column(name = "CATEGORY_TYPE", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(name = "PATH", length = 50, nullable = false)
    private String path;


}
