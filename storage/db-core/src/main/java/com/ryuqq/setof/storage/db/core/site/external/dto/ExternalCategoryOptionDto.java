package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.querydsl.core.annotations.QueryProjection;

public class ExternalCategoryOptionDto {

    private long siteId;
    private String externalCategoryId;
    private long optionGroupId;
    private Long optionId;
    private String optionValue;

    @QueryProjection
    public ExternalCategoryOptionDto(long siteId, String externalCategoryId, long optionGroupId, Long optionId, String optionValue) {
        this.siteId = siteId;
        this.externalCategoryId = externalCategoryId;
        this.optionGroupId = optionGroupId;
        this.optionId = optionId;
        this.optionValue = optionValue;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getExternalCategoryId() {
        return externalCategoryId;
    }

    public long getOptionGroupId() {
        return optionGroupId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public String getOptionValue() {
        return optionValue;
    }
}
