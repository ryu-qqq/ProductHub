package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.core.CategoryType;
import com.ryuqq.setof.core.TargetGroup;
import com.ryuqq.setof.domain.core.generic.LastDomainIdProvider;

public record Category(
        long id,
        String categoryName,
        int depth,
        long parentCategoryId,
        boolean displayYn,
        TargetGroup targetGroup,
        CategoryType categoryType,
        String path
) implements LastDomainIdProvider {
    @Override
    public Long getId() {
        return id;
    }
}
