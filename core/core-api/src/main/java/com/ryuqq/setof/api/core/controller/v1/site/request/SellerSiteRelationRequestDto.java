package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.external.ExternalSiteSellerRelationCommand;

import java.util.List;

public record SellerSiteRelationRequestDto(
        long sellerId,
        List<Long> siteIds
) {
    public ExternalSiteSellerRelationCommand toSellerSiteRelationCommand(){
        return new ExternalSiteSellerRelationCommand(sellerId, siteIds);
    }

}
