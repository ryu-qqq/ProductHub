package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.ProductImageType;
import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImageEntity;

import java.util.Objects;

public class ProductGroupImage {
    private final long productGroupImageId;
    private final long productGroupId;
    private final ProductImageType productImageType;
    private final String imageUrl;
    private String originUrl;
    private boolean deleteYn;

    public ProductGroupImage(long productGroupImageId, long productGroupId, ProductImageType productImageType, String imageUrl, String originUrl) {
        this.productGroupImageId = productGroupImageId;
        this.productGroupId = productGroupId;
        this.productImageType = productImageType;
        this.imageUrl = imageUrl;
        this.originUrl = originUrl;
        this.deleteYn = false;
    }

    public long getProductGroupImageId() {
        return productGroupImageId;
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

    public String getOriginUrl() {
        return originUrl;
    }

    public boolean needsUpdate(String originUrl) {
        return !this.originUrl.equals(originUrl);
    }

    public void updateFromCommand(String originUrl) {
        this.originUrl = originUrl;
    }

    public void delete(){
        this.deleteYn = true;
    }

    public boolean isDeleteYn() {
        return deleteYn;
    }

    public ProductGroupImageEntity toEntity() {
        return new ProductGroupImageEntity(
                productGroupImageId,
                productGroupId,
                productImageType,
                imageUrl,
                originUrl,
                deleteYn);
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ProductGroupImage that = (ProductGroupImage) object;
        return
                productGroupImageId == that.productGroupImageId &&
                productGroupId == that.productGroupId &&
                productImageType == that.productImageType &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(originUrl, that.originUrl) &&
                Objects.equals(deleteYn, that.deleteYn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupImageId, productGroupId, productImageType, imageUrl, originUrl, deleteYn);
    }

    @Override
    public String toString() {
        return "ProductGroupImage{" +
                "productGroupImageId=" + productGroupImageId +
                ", productGroupId=" + productGroupId +
                ", productImageType=" + productImageType +
                ", imageUrl='" + imageUrl + '\'' +
                ", originUrl='" + originUrl + '\'' +
                ", deleteYn=" + deleteYn +
                '}';
    }
}
