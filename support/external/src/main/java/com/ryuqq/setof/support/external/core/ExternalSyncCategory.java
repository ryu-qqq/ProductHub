package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.CategoryType;
import com.ryuqq.setof.enums.core.TargetGroup;

public record ExternalSyncCategory(
        long siteId,
        String externalCategoryId,
        String externalExtraCategoryId,
        String description,
        long categoryId,
        String categoryName,
        TargetGroup targetGroup,
        CategoryType categoryType
) {}
