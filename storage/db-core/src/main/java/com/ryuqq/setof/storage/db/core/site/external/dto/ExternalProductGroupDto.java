package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.SyncStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ExternalProductGroupDto {

    private final long id;
    private final long siteId;
    private final String siteName;
    private final long productGroupId;
    private final long policyId;
    private final String externalProductGroupId;
    private final String productName;
    private final String originName;
    private final BigDecimal regularPrice;
    private final BigDecimal currentPrice;
    private final SyncStatus status;
    private final boolean soldOutYn;
    private final boolean displayYn;
    private final Long sellerId;
    private final long internalBrandId;
    private final long internalCategoryId;
    private final String categoryPath;
    private final String externalBrandId;
    private final String externalCategoryId;
    private final String externalExtraCategoryId;
    private final LocalDateTime insertDate;
    private final LocalDateTime updateDate;
    private final List<ExternalProductImageDto> externalProductImages;

    @QueryProjection
    public ExternalProductGroupDto(long id, long siteId, String siteName, long productGroupId, long policyId, String externalProductGroupId, String productName, String originName, BigDecimal regularPrice, BigDecimal currentPrice, SyncStatus status, boolean soldOutYn, boolean displayYn, Long sellerId, long internalBrandId, long internalCategoryId, String categoryPath, String externalBrandId, String externalCategoryId, String externalExtraCategoryId, LocalDateTime insertDate, LocalDateTime updateDate, List<ExternalProductImageDto> externalProductImages) {
        this.id = id;
        this.siteId = siteId;
        this.siteName = siteName;
        this.productGroupId = productGroupId;
        this.policyId = policyId;
        this.externalProductGroupId = externalProductGroupId;
        this.productName = productName;
        this.originName = originName;
        this.regularPrice = regularPrice;
        this.currentPrice = currentPrice;
        this.status = status;
        this.soldOutYn = soldOutYn;
        this.displayYn = displayYn;
        this.sellerId = sellerId;
        this.internalBrandId = internalBrandId;
        this.internalCategoryId = internalCategoryId;
        this.categoryPath = categoryPath;
        this.externalBrandId = externalBrandId;
        this.externalCategoryId = externalCategoryId;
        this.externalExtraCategoryId = externalExtraCategoryId;
        this.insertDate = insertDate;
        this.updateDate = updateDate;
        this.externalProductImages = externalProductImages;
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

    public String getExternalProductGroupId() {
        return externalProductGroupId;
    }

    public String getProductName() {
        return productName;
    }

    public String getOriginName() {
        return originName;
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

    public String getCategoryPath() {
        return categoryPath;
    }

    public String getExternalBrandId() {
        return externalBrandId;
    }

    public String getExternalCategoryId() {
        return externalCategoryId;
    }

    public String getExternalExtraCategoryId() {
        return externalExtraCategoryId;
    }

    public String getSiteName() {
        return siteName;
    }

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public List<ExternalProductImageDto> getExternalProductImages() {
        return externalProductImages;
    }

}
