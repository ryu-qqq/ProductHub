package com.ryuqq.setof.storage.db.core.site.crawl;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "CRAWL_PRODUCT")
@Entity
public class CrawlProductEntity extends BaseEntity {

    @Column(name = "SITE_ID", nullable = false)
    private long siteId;

    @Column(name = "SITE_PRODUCT_ID", nullable = false, length = 255)
    private String siteProductId;

    @Column(name = "PRODUCT_GROUP_ID")
    private Long productGroupId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    public CrawlProductEntity() {}

    public CrawlProductEntity(long siteId, String productName, String siteProductId) {
        this.siteId = siteId;
        this.productName = productName;
        this.siteProductId = siteProductId;
        this.productGroupId = null;
    }



    public long getSiteId() {
        return siteId;
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

    public void setProductGroupId(Long productGroupId) {
        this.productGroupId = productGroupId;
    }

}
