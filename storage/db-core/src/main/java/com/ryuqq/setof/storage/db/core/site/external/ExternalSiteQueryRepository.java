package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalSiteSellerRelationDto;

import java.util.List;

public interface ExternalSiteQueryRepository {

    boolean existBySellerIdAndSitId(long sellerId, long siteId);
    List<ExternalSiteSellerRelationDto> fetchBySellerId(List<Long> sellerId);

}
