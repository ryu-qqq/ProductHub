package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.site.dto.SellerSiteRelationDto;

import java.util.List;

public interface ExternalSiteQueryRepository {

    List<SellerSiteRelationDto> fetchSellerSiteRelation(Long siteId, List<Long> sellerIds);

}
