package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.math.BigDecimal;

public class ExternalProductDto {

    private final String externalProductGroupId;
    private final String externalProductId;
    private final String optionValue;
    private final int quantity;
    private final BigDecimal additionalPrice;
    private final boolean soldOutYn;
    private final boolean displayYn;

    @QueryProjection
    public ExternalProductDto(String externalProductGroupId, String externalProductId, String optionValue, int quantity, BigDecimal additionalPrice, boolean soldOutYn, boolean displayYn) {
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
