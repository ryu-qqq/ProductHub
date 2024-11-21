package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.SellerSiteRelationCommand;

import java.util.List;

public record SellerSiteRelationRequestDto(
        long siteId,
        List<Long> sellerIds
) {
    public SellerSiteRelationCommand toSellerSiteRelationCommand(){
        return new SellerSiteRelationCommand(siteId, sellerIds);
    }

}
