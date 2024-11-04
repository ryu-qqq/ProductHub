package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "HEADER_CONFIGURATIONS")
@Entity
public class HeaderConfigurationEntity extends BaseEntity {

    @Column(name = "CONFIGURATION_NAME", nullable = false)
    private String configurationName;

    @Column(name = "HEADER_NAME", nullable = false)
    private String headerName;

    @Column(name = "HEADER_VALUE", nullable = false)
    private String headerValue;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive;

    @Column(name = "DESCRIPTION")
    private String description;

    protected HeaderConfigurationEntity() {}

    public HeaderConfigurationEntity(String configurationName, String headerName, String headerValue, Boolean isActive, String description) {
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
