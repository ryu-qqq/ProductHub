package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.SiteType;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

@Table(name = "SITE")
@Entity
public class SiteEntity extends BaseEntity {

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "BASE_URL", nullable = false, length = 255)
    private String baseUrl;

    @Column(name = "COUNTRY_CODE", nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private Origin countryCode;

    @Column(name = "SITE_TYPE", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private SiteType siteType;

    protected SiteEntity() {}

    public SiteEntity(String name, String baseUrl, Origin countryCode, SiteType siteType) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.countryCode = countryCode;
        this.siteType = siteType;
    }

    public String getName() {
        return name;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Origin getCountryCode() {
        return countryCode;
    }

    public SiteType getSiteType() {
        return siteType;
    }
}
