package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.SiteName;

import java.util.List;

public interface ExternalCategoryOptionQueryService {
    List<ExternalCategoryOption> findExternalCategoryOptions(SiteName siteName, long categoryId);
}
