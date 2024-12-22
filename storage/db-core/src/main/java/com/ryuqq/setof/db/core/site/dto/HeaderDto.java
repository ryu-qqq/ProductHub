package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;

public class HeaderDto {

    private final String headerName;

    private final String headerValue;

    private final Integer weight;

    private final Boolean isRequired;

    private final Integer priority;

    private final String notes;

    @QueryProjection
    public HeaderDto(String headerName, String headerValue, Integer weight, Boolean isRequired, Integer priority, String notes) {
        this.headerName = headerName;
        this.headerValue = headerValue;
        this.weight = weight;
        this.isRequired = isRequired;
        this.priority = priority;
        this.notes = notes;
    }

    public String getHeaderName() {
        return headerName;
    }

    public String getHeaderValue() {
        return headerValue;
    }

    public Integer getWeight() {
        return weight;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getNotes() {
        return notes;
    }
}
