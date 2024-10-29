package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.core.CategoryType;
import com.ryuqq.setof.core.TargetGroup;
import com.ryuqq.setof.storage.db.core.category.dto.CategoryDto;

public record CategoryRecord(
        long id,
        String categoryName,
        int depth,
        long parentCategoryId,
        boolean displayYn,
        TargetGroup targetGroup,
        CategoryType categoryType,
        String path
){

    public static CategoryRecord toCategoryRecord(CategoryDto category) {
        return new CategoryRecord(
                category.getId(),
                category.getCategoryName(),
                category.getDepth(),
                category.getParentCategoryId(),
                category.isDisplayYn(),
                category.getTargetGroup(),
                category.getCategoryType(),
                category.getPath()
        );
    }

}
