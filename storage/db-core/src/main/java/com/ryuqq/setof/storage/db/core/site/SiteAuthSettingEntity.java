package com.ryuqq.setof.storage.db.core.site;


import com.ryuqq.setof.core.AuthType;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

@Table(name = "SITE_AUTH_SETTING")
@Entity
public class SiteAuthSettingEntity extends BaseEntity {

    @Column(name = "SITE_ID", nullable = false)
    private long siteId;

    @Enumerated(EnumType.STRING)
    @Column(name = "AUTH_TYPE", nullable = false)
    private AuthType authType;

    @Column(name = "AUTH_ENDPOINT")
    private String authEndpoint;

    @Column(name = "AUTH_HEADERS", nullable = true, length = 255)
    private String authHeaders;

    @Column(name = "AUTH_PAYLOAD")
    private String authPayload;

    protected SiteAuthSettingEntity() {}

    public SiteAuthSettingEntity(long siteId, AuthType authType, String authEndpoint, String authHeaders, String authPayload) {
        this.siteId = siteId;
        this.authType = authType;
        this.authEndpoint = authEndpoint;
        this.authHeaders = authHeaders;
        this.authPayload = authPayload;
    }

    public long getSiteId() {
        return siteId;
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
