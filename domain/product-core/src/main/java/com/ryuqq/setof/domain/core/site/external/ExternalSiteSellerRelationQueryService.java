package com.ryuqq.setof.domain.core.site.external;

import java.util.List;

public interface ExternalSiteSellerRelationQueryService {
    boolean existBySellerIdAndSitId(long sellerId, long siteId);
    List<ExternalSiteSellerRelation> findExternalSiteSellerRelation(List<Long> sellerIds);

}
