package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "MALL_CATEGORY_OPTION")
@Entity
public class MallCategoryOptionEntity extends BaseEntity {

    @Column(name = "SITE_ID", nullable = false)
    private Long siteId;

    @Column(name = "EXTERNAL_CATEGORY_ID", nullable = false)
    private Long externalCategoryId;

    @Column(name = "OPTION_VALUE", nullable = false, length = 255)
    private String optionValue;

    public MallCategoryOptionEntity() {}

    public MallCategoryOptionEntity(Long siteId, Long externalCategoryId, String optionValue) {
        this.siteId = siteId;
        this.externalCategoryId = externalCategoryId;
        this.optionValue = optionValue;
    }
}