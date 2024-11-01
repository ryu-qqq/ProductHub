package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "CRAWL_MAPPING")
@Entity
public class CrawlMappingEntity extends BaseEntity {

    @Column(name = "SITE_ID", nullable = false)
    private long siteId;

    @Column(name = "CRAWL_SETTING_ID", nullable = false)
    private long crawlSettingId;

    @Column(name = "AUTH_SETTING_ID", nullable = false)
    private long authSettingId;

    protected CrawlMappingEntity() {}

    public CrawlMappingEntity(long siteId, long crawlSettingId, long authSettingId) {
        this.siteId = siteId;
        this.crawlSettingId = crawlSettingId;
        this.authSettingId = authSettingId;
    }
}
