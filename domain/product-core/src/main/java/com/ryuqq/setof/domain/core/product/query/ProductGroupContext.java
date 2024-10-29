package com.ryuqq.setof.domain.core.product.query;

import java.util.List;
import java.util.Set;

public class ProductGroupContext {
    private ProductGroup productGroup;
    private ProductDelivery productDelivery;
    private ProductNotice productNotice;
    private List<ProductGroupImage> productGroupImages;
    private ProductDetailDescription productDetailDescription;
    private Set<ProductContext> product;

    protected ProductGroupContext() {}

    public ProductGroupContext(ProductGroup productGroup, ProductDelivery productDelivery, ProductNotice productNotice, List<ProductGroupImage> productGroupImages, ProductDetailDescription productDetailDescription, Set<ProductContext> product) {
        this.productGroup = productGroup;
        this.productDelivery = productDelivery;
        this.productNotice = productNotice;
        this.productGroupImages = productGroupImages;
        this.productDetailDescription = productDetailDescription;
        this.product = product;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public ProductDelivery getProductDelivery() {
        return productDelivery;
    }

    public ProductNotice getProductNotice() {
        return productNotice;
    }

    public List<ProductGroupImage> getProductGroupImages() {
        return productGroupImages;
    }

    public ProductDetailDescription getProductDetailDescription() {
        return productDetailDescription;
    }

    public Set<ProductContext> getProduct() {
        return product;
    }

    protected void setProduct(Set<ProductContext> product) {
        this.product = product;
    }

}
