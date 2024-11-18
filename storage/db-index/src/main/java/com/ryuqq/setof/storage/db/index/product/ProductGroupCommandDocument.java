package com.ryuqq.setof.storage.db.index.product;

import java.math.BigDecimal;

public class ProductGroupCommandDocument {

    private final Long brandId;
    private final Long categoryId;
    private final Long sellerId;
    private final String productGroupName;
    private final String styleCode;
    private final String productCondition;
    private final String managementType;
    private final String optionType;
    private final BigDecimal regularPrice;
    private final BigDecimal currentPrice;
    private final boolean soldOutYn;
    private final boolean displayYn;
    private final String status;
    private final String keywords;

    public ProductGroupCommandDocument(Long brandId, Long categoryId, Long sellerId, String productGroupName, String styleCode, String productCondition, String managementType, String optionType, BigDecimal regularPrice, BigDecimal currentPrice, boolean soldOutYn, boolean displayYn, String status, String keywords) {
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.sellerId = sellerId;
        this.productGroupName = productGroupName;
        this.styleCode = styleCode;
        this.productCondition = productCondition;
        this.managementType = managementType;
        this.optionType = optionType;
        this.regularPrice = regularPrice;
        this.currentPrice = currentPrice;
        this.soldOutYn = soldOutYn;
        this.displayYn = displayYn;
        this.status = status;
        this.keywords = keywords;
    }


}