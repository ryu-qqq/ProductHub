package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.util.List;

public class ExternalSiteSellerRelationDto {

    private final long sellerId;
    private final List<ExternalProductPolicyDto> externalProductPolicyDtos;

    @QueryProjection
    public ExternalSiteSellerRelationDto(long sellerId, List<ExternalProductPolicyDto> externalProductPolicyDtos) {
        this.sellerId = sellerId;
        this.externalProductPolicyDtos = externalProductPolicyDtos;
    }

    public long getSellerId() {
        return sellerId;
    }

    public List<ExternalProductPolicyDto> getExternalProductPolicyDtos() {
        return externalProductPolicyDtos;
    }
}
