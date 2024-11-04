package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;

public class HeaderConfigurationDto {

    private final String configurationName;

    private final String headerName;

    private final String headerValue;

    private final Boolean isActive;

    private final String description;

    @QueryProjection
    public HeaderConfigurationDto(String configurationName, String headerName, String headerValue, Boolean isActive, String description) {
        this.configurationName = configurationName;
        this.headerName = headerName;
        this.headerValue = headerValue;
        this.isActive = isActive;
        this.description = description;
    }

    public String getConfigurationName() {
        return configurationName;
    }

    public String getHeaderName() {
        return headerName;
    }

    public String getHeaderValue() {
        return headerValue;
    }

    public Boolean getActive() {
        return isActive;
    }

    public String getDescription() {
        return description;
    }
}
