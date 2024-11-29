package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.SyncStatus;

import java.math.BigDecimal;

public class ExternalProductDto {

    private final long id;
    private final long siteId;
    private final long productGroupId;
    private final long policyId;
    private final String externalProductId;
    private final String productName;
    private final BigDecimal regularPrice;
    private final BigDecimal currentPrice;
    private final SyncStatus status;
    private final boolean soldOutYn;
    private final boolean displayYn;
    private final Long sellerId;
    private final long internalBrandId;
    private final long internalCategoryId;
    private final String externalBrandId;
    private final String externalCategoryId;

    @QueryProjection
    public ExternalProductDto(long id, long siteId, long productGroupId, long policyId, String externalProductId, String productName, BigDecimal regularPrice, BigDecimal currentPrice, SyncStatus status, boolean soldOutYn, boolean displayYn, Long sellerId, long internalBrandId, long internalCategoryId, String externalBrandId, String externalCategoryId) {
        this.id = id;
        this.siteId = siteId;
        this.productGroupId = productGroupId;
        this.policyId = policyId;
        this.externalProductId = externalProductId;
        this.productName = productName;
        this.regularPrice = regularPrice;
        this.currentPrice = currentPrice;
        this.status = status;
        this.soldOutYn = soldOutYn;
        this.displayYn = displayYn;
        this.sellerId = sellerId;
        this.internalBrandId = internalBrandId;
        this.internalCategoryId = internalCategoryId;
        this.externalBrandId = externalBrandId;
        this.externalCategoryId = externalCategoryId;
    }

    public long getId() {
        return id;
    }

    public long getSiteId() {
        return siteId;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public long getPolicyId() {
        return policyId;
    }

    public String getExternalProductId() {
        return externalProductId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public SyncStatus getStatus() {
        return status;
    }

    public boolean isSoldOutYn() {
        return soldOutYn;
    }

    public boolean isDisplayYn() {
        return displayYn;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public long getInternalBrandId() {
        return internalBrandId;
    }

    public long getInternalCategoryId() {
        return internalCategoryId;
    }

    public String getExternalBrandId() {
        return externalBrandId;
    }

    public String getExternalCategoryId() {
        return externalCategoryId;
    }
}
