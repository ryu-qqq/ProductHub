package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.AuthType;
import com.ryuqq.setof.storage.db.core.site.SiteAuthSettingEntity;

public class CrawlAuthSetting {

    private AuthType authType;
    private String authEndpoint;
    private String authHeaders;
    private String authPayload;

    public CrawlAuthSetting(AuthType authType, String authEndpoint, String authHeaders, String authPayload) {
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

    public SiteAuthSettingEntity toSiteAuthSettingEntity(long siteId) {
        return new SiteAuthSettingEntity(
                siteId,
                authType,
                authEndpoint,
                authHeaders,
                authPayload
        );
    }
}

