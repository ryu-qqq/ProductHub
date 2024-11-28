package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.enums.core.CategoryType;
import com.ryuqq.setof.enums.core.TargetGroup;

public record Category(
        long id,
        String categoryName,
        int depth,
        long parentCategoryId,
        boolean displayYn,
        TargetGroup targetGroup,
        CategoryType categoryType,
        String path
){}
