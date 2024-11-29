package com.ryuqq.setof.domain.core.site.external;

import java.util.List;

public interface ExternalCategoryOptionQueryService {
    List<ExternalCategoryOption> fetchBySiteIdAndCategoryIds(long siteId, List<Long> categoryIds);
}
