package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "CRAWL_ENDPOINT")
@Entity
public class CrawlEndpointEntity extends BaseEntity {

    @Column(name = "SITE_ID", nullable = false)
    private long siteId;

    @Column(name = "ENDPOINT_URL", nullable = false, length = 255)
    private String endPointUrl;

    @Column(name = "CRAWL_FREQUENCY", nullable = false)
    private int crawlFrequency;

    protected CrawlEndpointEntity() {}

    public CrawlEndpointEntity(long siteId, String endPointUrl, int crawlFrequency) {
        this.siteId = siteId;
        this.endPointUrl = endPointUrl;
        this.crawlFrequency = crawlFrequency;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public int getCrawlFrequency() {
        return crawlFrequency;
    }
}
