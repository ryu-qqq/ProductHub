package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;

public class UserAgentDto {

    private final String userAgent;
    private final Integer weight;
    private final String deviceType;
    private final String os;
    private final String browserVersion;

    @QueryProjection
    public UserAgentDto(String userAgent, Integer weight, String deviceType, String os, String browserVersion) {
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
