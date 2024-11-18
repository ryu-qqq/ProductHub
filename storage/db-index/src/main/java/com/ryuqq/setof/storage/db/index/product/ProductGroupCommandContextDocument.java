package com.ryuqq.setof.storage.db.index.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "product-group-command-context")
public class ProductGroupCommandContextDocument {

    @Id
    private Long productGroupId;
    private final ProductGroupCommandDocument productGroupCommandDocument;
    private final ProductNoticeDocument productNotice;
    private final ProductDeliveryDocument productDelivery;
    private final List<ProductGroupImageDocument> productImages;
    private final ProductDetailDescriptionDocument detailDescription;
    private final List<ProductCommandDocument> products;

    public ProductGroupCommandContextDocument(Long productGroupId, ProductGroupCommandDocument productGroupCommandDocument, ProductNoticeDocument productNotice, ProductDeliveryDocument productDelivery, List<ProductGroupImageDocument> productImages, ProductDetailDescriptionDocument detailDescription, List<ProductCommandDocument> products) {
        this.productGroupId = productGroupId;
        this.productGroupCommandDocument = productGroupCommandDocument;
        this.productNotice = productNotice;
        this.productDelivery = productDelivery;
        this.productImages = productImages;
        this.detailDescription = detailDescription;
        this.products = products;
    }

    public Long getProductGroupId() {
        return productGroupId;
    }

    public ProductGroupCommandDocument getProductGroupCommandDocument() {
        return productGroupCommandDocument;
    }

    public ProductNoticeDocument getProductNotice() {
        return productNotice;
    }

    public ProductDeliveryDocument getProductDelivery() {
        return productDelivery;
    }

    public List<ProductGroupImageDocument> getProductImages() {
        return productImages;
    }

    public ProductDetailDescriptionDocument getDetailDescription() {
        return detailDescription;
    }

    public List<ProductCommandDocument> getProducts() {
        return products;
    }
}