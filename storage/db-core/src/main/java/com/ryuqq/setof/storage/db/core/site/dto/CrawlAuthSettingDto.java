package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.core.AuthType;

public class CrawlAuthSettingDto {

    private AuthType authType;
    private String authEndpoint;
    private String authHeaders;
    private String authPayload;

    @QueryProjection
    public CrawlAuthSettingDto(AuthType authType, String authEndpoint, String authHeaders, String authPayload) {
        this.authType = authType;
        this.authEndpoint = authEndpoint;
        this.authHeaders = authHeaders;
        this.authPayload = authPayload;
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
