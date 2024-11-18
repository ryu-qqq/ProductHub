package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.enums.core.AuthType;

public class CrawlAuthSetting {

    private long authSettingId;
    private AuthType authType;
    private String authEndpoint;
    private String authHeaders;
    private String authPayload;

    public CrawlAuthSetting(long authSettingId, AuthType authType, String authEndpoint, String authHeaders, String authPayload) {
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


    public boolean updateIfChanged(CrawlAuthSettingCommand command) {
        boolean isUpdated = false;

        if (!this.authType.equals(command.authType())) {
            return true;
        }
        if (!this.authEndpoint.equals(command.authEndpoint())) {
            return true;
        }
        if (!this.authHeaders.equals(command.authHeaders())) {
            return true;
        }
        if (!this.authPayload.equals(command.authPayload())) {
            return true;
        }

        return isUpdated;
    }


}

