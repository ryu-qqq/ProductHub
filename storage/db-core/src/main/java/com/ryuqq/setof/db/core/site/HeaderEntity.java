package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "HEADERS")
@Entity
public class HeaderEntity extends BaseEntity {

    @Column(name = "HEADER_NAME", nullable = false)
    private String headerName;

    @Column(name = "HEADER_VALUE", nullable = false)
    private String headerValue;

    @Column(name = "WEIGHT", nullable = false)
    private Integer weight;

    @Column(name = "IS_REQUIRED", nullable = false)
    private Boolean isRequired;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "NOTES")
    private String notes;

    protected HeaderEntity() {}

    public HeaderEntity(String headerName, String headerValue, Integer weight, Boolean isRequired, Integer priority, String notes) {
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
