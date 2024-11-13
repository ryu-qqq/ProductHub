package com.ryuqq.setof.domain.core.site;


public class CrawlProduct {

    private long crawlProductId;
    private long siteId;
    private String siteName;
    private String siteProductId;
    private String productName;
    private Long productGroupId;


    public CrawlProduct(long crawlProductId, long siteId, String siteName, String siteProductId, String productName, Long productGroupId) {
        this.crawlProductId = crawlProductId;
        this.siteId = siteId;
        this.siteName = siteName;
        this.siteProductId = siteProductId;
        this.productName = productName;
        this.productGroupId = productGroupId;
    }

    public long getCrawlProductId() {
        return crawlProductId;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getSiteProductId() {
        return siteProductId;
    }

    public Long getProductGroupId() {
        return productGroupId;
    }

    public String getProductName() {
        return productName;
    }

    public String getSiteName() {
        return siteName;
    }
}
