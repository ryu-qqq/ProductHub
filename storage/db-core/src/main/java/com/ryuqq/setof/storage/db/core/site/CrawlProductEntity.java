package com.ryuqq.setof.storage.db.core.site;

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

    @Column(name = "BRAND_ID")
    private String brandId;

    @Column(name = "CATEGORY_ID")
    private String categoryId;


}
