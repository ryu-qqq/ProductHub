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

    @Column(name = "CRAWL_MAPPING_ID", nullable = false)
    private long crawlMappingId;

    @Column(name = "ENDPOINT_URL", nullable = false, length = 255)
    private String endPointUrl;

    @Column(name = "PARAMETERS", nullable = false, length = 255)
    private String parameters;

    protected CrawlEndpointEntity() {}

    public CrawlEndpointEntity(long siteId, long crawlMappingId, String endPointUrl, String parameters) {
        this.siteId = siteId;
        this.crawlMappingId = crawlMappingId;
        this.endPointUrl = endPointUrl;
        this.parameters = parameters;
    }

    public long getSiteId() {
        return siteId;
    }

}
