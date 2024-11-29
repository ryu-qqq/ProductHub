package com.ryuqq.setof.domain.core.product;

import java.util.Objects;

public class ProductGroupConfig{

    private final long configId;
    private final long sellerId;
    private final long productGroupId;
    private final boolean activeYn;

    public ProductGroupConfig(long configId, long sellerId, long productGroupId, boolean activeYn) {
        this.configId = configId;
        this.sellerId = sellerId;
        this.productGroupId = productGroupId;
        this.activeYn = activeYn;
    }

    public long getConfigId() {
        return configId;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public boolean isActiveYn() {
        return activeYn;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ProductGroupConfig that = (ProductGroupConfig) object;
        return configId == that.configId && sellerId == that.sellerId && productGroupId == that.productGroupId && activeYn == that.activeYn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(configId, sellerId, productGroupId, activeYn);
    }

    @Override
    public String toString() {
        return "ProductGroupConfig{" +
                "configId=" + configId +
                ", sellerId=" + sellerId +
                ", productGroupId=" + productGroupId +
                ", activeYn=" + activeYn +
                '}';
    }
}
