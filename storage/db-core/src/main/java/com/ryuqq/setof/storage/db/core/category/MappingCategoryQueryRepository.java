package com.ryuqq.setof.storage.db.core.category;

import com.ryuqq.setof.storage.db.core.category.dto.MappingCategoryDto;

import java.util.List;

public interface MappingCategoryQueryRepository {

    List<MappingCategoryDto> fetchByCategoryIdAndSiteId(long siteId, List<Long> categoryIds);
}
