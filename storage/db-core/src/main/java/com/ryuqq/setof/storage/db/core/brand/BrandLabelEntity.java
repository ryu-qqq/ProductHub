package com.ryuqq.setof.storage.db.core.brand;

import com.ryuqq.setof.core.LanguageCode;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Table(name = "BRAND_LABEL")
@Entity
public class BrandLabelEntity extends BaseEntity {

    @Column(name = "BRAND_ID", nullable = false)
    private long brandId;

    @Column(name = "LANGUAGE_CODE", length = 5, nullable = false)
    private LanguageCode languageCode;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    protected BrandLabelEntity() {
    }

    public BrandLabelEntity(long brandId, LanguageCode languageCode, String name) {
        this.brandId = brandId;
        this.languageCode = languageCode;
        this.name = name;
    }

    public long getBrandId() {
        return brandId;
    }

    public LanguageCode getLanguageCode() {
        return languageCode;
    }

    public String getName() {
        return name;
    }
}
