package com.ryuqq.setof.storage.db.index.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.WriteTypeHint;

import java.util.List;

@Document(indexName = "product-group-command-context", writeTypeHint = WriteTypeHint.FALSE)
public class ProductGroupCommandContextDocument {

    @Id
    private String productGroupId;
    private ProductGroupCommandDocument productGroupCommandDocument;
    private ProductNoticeDocument productNotice;
    private ProductDeliveryDocument productDelivery;
    private List<ProductGroupImageDocument> productImages;
    private ProductDetailDescriptionDocument detailDescription;
    private List<ProductCommandDocument> products;

    protected ProductGroupCommandContextDocument(){};

    public ProductGroupCommandContextDocument(Long productGroupId, ProductGroupCommandDocument productGroupCommandDocument, ProductNoticeDocument productNotice, ProductDeliveryDocument productDelivery, List<ProductGroupImageDocument> productImages, ProductDetailDescriptionDocument detailDescription, List<ProductCommandDocument> products) {
        this.productGroupId = String.valueOf(productGroupId);
        this.productGroupCommandDocument = productGroupCommandDocument;
        this.productNotice = productNotice;
        this.productDelivery = productDelivery;
        this.productImages = productImages;
        this.detailDescription = detailDescription;
        this.products = products;
    }

    public String getProductGroupId() {
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