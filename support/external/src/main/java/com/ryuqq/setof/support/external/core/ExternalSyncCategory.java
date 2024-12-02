package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.CategoryType;
import com.ryuqq.setof.enums.core.TargetGroup;

public record ExternalSyncCategory(
        String externalCategoryId,
        String categoryName,
        TargetGroup targetGroup,
        CategoryType categoryType
) {}
