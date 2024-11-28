package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "PRODUCT_GROUP_NAME_CONFIG")
public class ProductGroupNameConfigEntity extends BaseEntity {

    @Column(name = "PRODUCT_GROUP_CONFIG_ID", nullable = false)
    private long productGroupConfigId;

    @Column(name = "COUNTRY_CODE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Origin countryCode;

    @Column(name = "CUSTOM_NAME", length = 255)
    private String customName;

    @Column(name = "DEFAULT_YN", nullable = false)
    private boolean defaultYn;

    protected ProductGroupNameConfigEntity() {}

    public ProductGroupNameConfigEntity(long productGroupConfigId, Origin countryCode, String customName, boolean defaultYn) {
        this.productGroupConfigId = productGroupConfigId;
        this.countryCode = countryCode;
        this.customName = customName;
        this.defaultYn = defaultYn;
    }

    public long getProductGroupConfigId() {
        return productGroupConfigId;
    }

    public Origin getCountryCode() {
        return countryCode;
    }

    public String getCustomName() {
        return customName;
    }

    public boolean isDefaultYn() {
        return defaultYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductGroupNameConfigEntity that = (ProductGroupNameConfigEntity) o;
        return productGroupConfigId == that.productGroupConfigId &&
                defaultYn == that.defaultYn &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(customName, that.customName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupConfigId, countryCode, customName, defaultYn);
    }

}
