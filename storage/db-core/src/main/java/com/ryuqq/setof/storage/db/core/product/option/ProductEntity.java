package com.ryuqq.setof.storage.db.core.product.option;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Table(name = "PRODUCT")
@Entity
public class ProductEntity extends BaseEntity {

    @Column(name = "PRODUCT_GROUP_ID", nullable = false)
    private long productGroupId;

    @Column(name = "SOLD_OUT_YN", nullable = false)
    private boolean soldOutYn;

    @Column(name = "DISPLAY_YN",  nullable = false)
    private boolean displayYn;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "ADDITIONAL_PRICE")
    private BigDecimal additionalPrice;

    protected ProductEntity() {}

    public ProductEntity(long productGroupId, boolean soldOutYn, boolean displayYn, int quantity, BigDecimal additionalPrice) {
        this.productGroupId = productGroupId;
        this.soldOutYn = soldOutYn;
        this.displayYn = displayYn;
        this.quantity = quantity;
        this.additionalPrice = additionalPrice;
    }
}
