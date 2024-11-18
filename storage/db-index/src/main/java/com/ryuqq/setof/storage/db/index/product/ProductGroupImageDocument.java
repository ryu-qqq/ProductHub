package com.ryuqq.setof.storage.db.index.product;

public class ProductGroupImageDocument {
    private String productImageType;
    private String imageUrl;

    public ProductGroupImageDocument(String productImageType, String imageUrl) {
        this.productImageType = productImageType;
        this.imageUrl = imageUrl;
    }
}
