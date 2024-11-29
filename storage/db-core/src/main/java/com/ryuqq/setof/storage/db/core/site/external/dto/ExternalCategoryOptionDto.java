package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.querydsl.core.annotations.QueryProjection;

public class ExternalCategoryOptionDto {

    private long siteId;
    private String externalCategoryId;
    private long optionId;
    private String optionValue;

    protected ExternalCategoryOptionDto() {}

    @QueryProjection
    public ExternalCategoryOptionDto(long siteId, String externalCategoryId, long optionId, String optionValue) {
        this.siteId = siteId;
        this.externalCategoryId = externalCategoryId;
        this.optionId = optionId;
        this.optionValue = optionValue;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getExternalCategoryId() {
        return externalCategoryId;
    }

    public long getOptionId() {
        return optionId;
    }

    public String getOptionValue() {
        return optionValue;
    }
}
