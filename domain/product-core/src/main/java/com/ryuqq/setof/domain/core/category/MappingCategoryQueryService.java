package com.ryuqq.setof.domain.core.category;

import java.util.List;

public interface MappingCategoryQueryService {

    List<MappingCategory> getMappingCategories(long siteId, List<Long> categoryIds);
}
