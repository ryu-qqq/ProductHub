package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductStorageFilterDto;

import java.util.List;

public interface ExternalProductQueryRepository {
    List<Long> fetchUnlinkedProductGroupIdsBySellerIdAndSiteId(long sellerId, List<Long> siteIds);
    List<ExternalProductDto> fetchByFilter(ExternalProductStorageFilterDto filter);
}
