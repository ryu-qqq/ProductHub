package com.ryuqq.setof.domain.core.product;

import java.util.Objects;

public class ProductDetailDescription {
    private long productGroupId;
    private String detailDescription;

    public ProductDetailDescription(long productGroupId, String detailDescription) {
        this.productGroupId = productGroupId;
        this.detailDescription = detailDescription;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ProductDetailDescription that = (ProductDetailDescription) object;
        return productGroupId == that.productGroupId && Objects.equals(detailDescription, that.detailDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupId, detailDescription);
    }

    @Override
    public String toString() {
        return "ProductDetailDescription{" +
                "productGroupId=" + productGroupId +
                ", detailDescription='" + detailDescription + '\'' +
                '}';
    }
}
