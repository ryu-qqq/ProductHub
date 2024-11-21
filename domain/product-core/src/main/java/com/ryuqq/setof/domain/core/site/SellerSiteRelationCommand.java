package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.storage.db.core.site.ExternalSiteSellerEntity;

import java.util.List;

public record SellerSiteRelationCommand(
        long siteId,
        List<Long> sellerIds
) {

    public List<ExternalSiteSellerEntity> toExternalSiteSellerEntities() {
        return sellerIds.stream()
                .map(aLong -> new ExternalSiteSellerEntity(siteId, aLong))
                .toList();
    }

}
