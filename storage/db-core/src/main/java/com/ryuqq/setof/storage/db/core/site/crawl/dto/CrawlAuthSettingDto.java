package com.ryuqq.setof.storage.db.core.site.crawl.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.AuthType;

public class CrawlAuthSettingDto {

    private long authSettingId;
    private AuthType authType;
    private String authEndpoint;
    private String authHeaders;
    private String authPayload;

    @QueryProjection
    public CrawlAuthSettingDto(long authSettingId, AuthType authType, String authEndpoint, String authHeaders, String authPayload) {
        this.authSettingId = authSettingId;
        this.authType = authType;
        this.authEndpoint = authEndpoint;
        this.authHeaders = authHeaders;
        this.authPayload = authPayload;
    }

    public long getAuthSettingId() {
        return authSettingId;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public String getAuthEndpoint() {
        return authEndpoint;
    }

    public String getAuthHeaders() {
        return authHeaders;
    }

    public String getAuthPayload() {
        return authPayload;
    }
}
