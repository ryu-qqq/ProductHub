package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;

public class CrawlProductDto {

    private long crawlProductId;
    private long siteId;
    private String siteName;
    private String siteProductId;
    private String productName;
    private Long productGroupId;

    @QueryProjection
    public CrawlProductDto(long crawlProductId, long siteId, String siteName, String siteProductId, String productName, Long productGroupId) {
        this.crawlProductId = crawlProductId;
        this.siteId = siteId;
        this.siteName = siteName;
        this.siteProductId = siteProductId;
        this.productGroupId = productGroupId;
    }

    public long getCrawlProductId() {
        return crawlProductId;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getSiteProductId() {
        return siteProductId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getProductGroupId() {
        return productGroupId;
    }
}
