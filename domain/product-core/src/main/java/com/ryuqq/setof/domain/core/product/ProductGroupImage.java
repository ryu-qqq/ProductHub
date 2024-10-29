package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.core.ProductImageType;

import java.util.Objects;

public class ProductGroupImage {
    private long productGroupId;
    private ProductImageType productImageType;
    private String imageUrl;

    public ProductGroupImage(long productGroupId, ProductImageType productImageType, String imageUrl) {
        this.productGroupId = productGroupId;
        this.productImageType = productImageType;
        this.imageUrl = imageUrl;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public ProductImageType getProductImageType() {
        return productImageType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ProductGroupImage that = (ProductGroupImage) object;
        return productGroupId == that.productGroupId && productImageType == that.productImageType && Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupId, productImageType, imageUrl);
    }

    @Override
    public String toString() {
        return "ProductGroupImage{" +
                "productGroupId=" + productGroupId +
                ", productImageType=" + productImageType +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
