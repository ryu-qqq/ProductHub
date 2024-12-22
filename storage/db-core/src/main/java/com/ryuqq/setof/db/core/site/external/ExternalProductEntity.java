package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Table(name = "EXTERNAL_PRODUCT")
@Entity
public class ExternalProductEntity extends BaseEntity {

    @Column(name = "EXTERNAL_PRODUCT_GROUP_ID", nullable = false)
    private String externalProductGroupId;

    @Column(name = "EXTERNAL_PRODUCT_ID", nullable = false)
    private String externalProductId;

    @Column(name = "OPTION_VALUE", nullable = false)
    private String optionValue;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "ADDITIONAL_PRICE", nullable = false)
    private BigDecimal additionalPrice;

    @Column(name = "SOLD_OUT_YN", nullable = false)
    private boolean soldOutYn;

    @Column(name = "DISPLAY_YN",  nullable = false)
    private boolean displayYn;

    protected ExternalProductEntity() {}

    public ExternalProductEntity(String externalProductGroupId, String externalProductId, String optionValue, int quantity, BigDecimal additionalPrice, boolean soldOutYn, boolean displayYn) {
        this.externalProductGroupId = externalProductGroupId;
        this.externalProductId = externalProductId;
        this.optionValue = optionValue;
        this.quantity = quantity;
        this.additionalPrice = additionalPrice;
        this.soldOutYn = soldOutYn;
        this.displayYn = displayYn;
    }

    public String getExternalProductGroupId() {
        return externalProductGroupId;
    }

    public String getExternalProductId() {
        return externalProductId;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getAdditionalPrice() {
        return additionalPrice;
    }

    public boolean isSoldOutYn() {
        return soldOutYn;
    }

    public boolean isDisplayYn() {
        return displayYn;
    }
}
