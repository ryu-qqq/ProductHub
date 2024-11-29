package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "EXTERNAL_CATEGORY_OPTION")
@Entity
public class ExternalCategoryOptionEntity extends BaseEntity {

    @Column(name = "SITE_ID", nullable = false)
    private Long siteId;

    @Column(name = "EXTERNAL_CATEGORY_ID", nullable = false)
    private String externalCategoryId;

    @Column(name = "OPTION_ID", nullable = false)
    private long optionId;

    @Column(name = "OPTION_VALUE", nullable = false, length = 255)
    private String optionValue;

    public ExternalCategoryOptionEntity() {}

    public ExternalCategoryOptionEntity(Long siteId, String externalCategoryId, String optionValue) {
        this.siteId = siteId;
        this.externalCategoryId = externalCategoryId;
        this.optionValue = optionValue;
    }


}