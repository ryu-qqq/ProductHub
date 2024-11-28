package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalCategoryOptionDto;

import java.util.List;

public interface ExternalCategoryOptionQueryRepository {

    List<ExternalCategoryOptionDto> fetchBySiteNameAndCategoryId(SiteName siteName, long categoryId);
}
