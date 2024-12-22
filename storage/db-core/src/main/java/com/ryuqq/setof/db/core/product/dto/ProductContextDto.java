package com.ryuqq.setof.storage.db.core.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.OptionName;

import java.math.BigDecimal;

public class ProductContextDto {
    private long productGroupId;
    private long productId;
    private int quantity;
    private boolean soldOutYn;
    private boolean displayYn;
    private long optionGroupId;
    private long optionDetailId;
    private OptionName optionName;
    private String optionValue;
    private BigDecimal additionalPrice;

    protected ProductContextDto() {}

    @QueryProjection
    public ProductContextDto(long productGroupId, long productId, int quantity, boolean soldOutYn, boolean displayYn, long optionGroupId, long optionDetailId, OptionName optionName, String optionValue, BigDecimal additionalPrice) {
        this.productGroupId = productGroupId;
        this.productId = productId;
        this.quantity = quantity;
        this.soldOutYn = soldOutYn;
        this.displayYn= displayYn;
        this.optionGroupId = optionGroupId;
        this.optionDetailId = optionDetailId;
        this.optionName = optionName;
        this.optionValue = optionValue;
        this.additionalPrice = additionalPrice;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isSoldOutYn() {
        return soldOutYn;
    }

    public boolean isDisplayYn() {
        return displayYn;
    }

    public long getOptionGroupId() {
        return optionGroupId;
    }

    public long getOptionDetailId() {
        return optionDetailId;
    }

    public OptionName getOptionName() {
        return optionName;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public BigDecimal getAdditionalPrice() {
        return additionalPrice;
    }
}
