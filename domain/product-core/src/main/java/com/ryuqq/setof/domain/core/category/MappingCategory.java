package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.enums.core.CategoryType;
import com.ryuqq.setof.enums.core.TargetGroup;

public record MappingCategory(
        long siteId,
        String externalCategoryId,
        String externalExtraCategoryId,
        String description,
        long categoryId,
        String categoryName,
        TargetGroup targetGroup,
        CategoryType categoryType

) {
}
