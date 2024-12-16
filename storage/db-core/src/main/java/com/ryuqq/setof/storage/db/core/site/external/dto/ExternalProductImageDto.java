package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.querydsl.core.annotations.QueryProjection;

public class ExternalProductImageDto {
    private final long productGroupId;
    private final long siteId;
    private final String externalProductGroupId;
    private final int displayOrder;
    private final String imageUrl;
    private final String originUrl;

    @QueryProjection
    public ExternalProductImageDto(long productGroupId, long siteId, String externalProductGroupId, int displayOrder, String imageUrl, String originUrl) {
        this.productGroupId = productGroupId;
        this.siteId = siteId;
        this.externalProductGroupId = externalProductGroupId;
        this.displayOrder = displayOrder;
        this.imageUrl = imageUrl;
        this.originUrl = originUrl;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getExternalProductGroupId() {
        return externalProductGroupId;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOriginUrl() {
        return originUrl;
    }
}
