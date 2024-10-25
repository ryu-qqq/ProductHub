package com.ryuqq.setof.producthub.core.api.controller.v1.category.response;

import com.ryuqq.setof.core.CategoryType;
import com.ryuqq.setof.core.TargetGroup;

public record CategoryResponse(
        long id,
        String categoryName,
        int depth,
        long parentCategoryId,
        boolean displayYn,
        TargetGroup targetGroup,
        CategoryType categoryType,
        String path
) {}
