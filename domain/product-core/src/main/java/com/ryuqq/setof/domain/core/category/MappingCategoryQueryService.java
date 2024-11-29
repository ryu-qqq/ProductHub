package com.ryuqq.setof.domain.core.category;

import java.util.List;

public interface MappingCategoryQueryService {

    List<MappingCategory> fetchBySiteIdAndCategoryIds(long siteId, List<Long> categoryIds);
}
