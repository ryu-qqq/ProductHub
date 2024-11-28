package com.ryuqq.setof.domain.core.site.external;


import com.ryuqq.setof.storage.db.core.site.external.ExternalSiteSellerEntity;

import java.util.List;

public record ExternalSiteSellerRelationCommand(
        long sellerId,
        List<Long> siteIds
) {
    public List<ExternalSiteSellerEntity> toExternalSiteSellerEntities() {
        return siteIds.stream()
                .map(site -> new ExternalSiteSellerEntity(site, sellerId))
                .toList();
    }
}
