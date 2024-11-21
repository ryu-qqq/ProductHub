package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.util.List;

public class SellerSiteRelationDto {

    private final long sellerId;
    private final List<SitePolicyDto> sitePolicies;

    @QueryProjection
    public SellerSiteRelationDto(long sellerId, List<SitePolicyDto> sitePolicies) {
        this.sellerId = sellerId;
        this.sitePolicies = sitePolicies;
    }

    public long getSellerId() {
        return sellerId;
    }

    public List<SitePolicyDto> getSitePolicies() {
        return sitePolicies;
    }
}
