package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalCategoryOptionDto;

import java.util.List;

public interface ExternalCategoryOptionQueryRepository {

    List<ExternalCategoryOptionDto> fetchBySiteIdAndCategoryIds(long siteId, List<Long> categoryIds);
}
