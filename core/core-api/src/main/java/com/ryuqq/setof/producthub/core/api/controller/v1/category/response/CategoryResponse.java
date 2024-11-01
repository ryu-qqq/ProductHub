package com.ryuqq.setof.producthub.core.api.controller.v1.category.response;

import com.ryuqq.setof.core.CategoryType;
import com.ryuqq.setof.core.TargetGroup;
import com.ryuqq.setof.domain.core.category.CategoryRecord;

public record CategoryResponse(
        long categoryId,
        String categoryName,
        int depth,
        long parentCategoryId,
        boolean displayYn,
        TargetGroup targetGroup,
        CategoryType categoryType,
        String path
) {
    public static CategoryResponse of(CategoryRecord category) {
        return new CategoryResponse(
                category.id(),
                category.categoryName(),
                category.depth(),
                category.parentCategoryId(),
                category.displayYn(),
                category.targetGroup(),
                category.categoryType(),
                category.path()
        );
    }

}
