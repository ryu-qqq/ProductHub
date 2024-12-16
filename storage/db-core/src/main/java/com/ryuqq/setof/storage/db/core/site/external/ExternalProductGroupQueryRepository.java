package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductGroupDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductGroupStorageFilterDto;

import java.util.List;

public interface ExternalProductGroupQueryRepository {
    long countByFilter(ExternalProductGroupStorageFilterDto filter);
    List<Long> fetchUnlinkedProductGroupIdsBySellerIdAndSiteId(long sellerId, List<Long> siteIds);

    List<Long> fetchIdsByFilter(ExternalProductGroupStorageFilterDto filter);
    List<ExternalProductGroupDto> fetchByIds(List<Long> externalProductIds);
}
