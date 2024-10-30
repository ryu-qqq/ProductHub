package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.core.CrawlType;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

@Table(name = "CRAWL_SETTING")
@Entity
public class CrawlSettingEntity extends BaseEntity {

    @Column(name = "SITE_ID", nullable = false)
    private long siteId;

    @Column(name = "CRAWL_FREQUENCY", nullable = false)
    private int crawlFrequency;

    @Enumerated(EnumType.STRING)
    @Column(name = "CRAWL_TYPE", nullable = false, length = 20)
    private CrawlType crawlType;

    protected CrawlSettingEntity() {}

    public CrawlSettingEntity(long siteId, int crawlFrequency, CrawlType crawlType) {
        this.siteId = siteId;
        this.crawlFrequency = crawlFrequency;
        this.crawlType = crawlType;
    }

    public long getSiteId() {
        return siteId;
    }

    public int getCrawlFrequency() {
        return crawlFrequency;
    }

    public CrawlType getCrawlType() {
        return crawlType;
    }
}
