package com.ryuqq.setof.api.core.controller.v1.category.response;

import com.ryuqq.setof.domain.core.category.CategoryRecord;
import com.ryuqq.setof.enums.core.CategoryType;
import com.ryuqq.setof.enums.core.TargetGroup;

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
