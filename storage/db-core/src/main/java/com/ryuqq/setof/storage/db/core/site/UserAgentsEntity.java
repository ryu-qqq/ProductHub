package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "USER_AGENTS")
@Entity
public class UserAgentsEntity extends BaseEntity {

    @Column(name = "user_agent", nullable = false)
    private String userAgent;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "device_type")
    private String deviceType;

    @Column(name = "os")
    private String os;

    @Column(name = "browser_version")
    private String browserVersion;

    protected UserAgentsEntity() {}

    public UserAgentsEntity(String userAgent, Integer weight, String deviceType, String os, String browserVersion) {
        this.userAgent = userAgent;
        this.weight = weight;
        this.deviceType = deviceType;
        this.os = os;
        this.browserVersion = browserVersion;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getOs() {
        return os;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }
}
