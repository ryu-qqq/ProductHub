package com.ryuqq.setof.domain.core.product;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ProductGroupContext {

    private final ProductGroup productGroup;
    private final ProductDelivery delivery;
    private final ProductNotice notice;
    private final List<ProductGroupImage> images;
    private final ProductDetailDescription detailDescription;
    private LinkedHashSet<Product> products;

    public ProductGroupContext(ProductGroup productGroup, ProductDelivery delivery, ProductNotice notice, List<ProductGroupImage> images, ProductDetailDescription detailDescription, LinkedHashSet<Product> products) {
        this.productGroup = productGroup;
        this.delivery = delivery;
        this.notice = notice;
        this.images = images;
        this.detailDescription = detailDescription;
        this.products = products;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public ProductDelivery getDelivery() {
        return delivery;
    }

    public ProductNotice getNotice() {
        return notice;
    }

    public List<ProductGroupImage> getImages() {
        return images;
    }

    public ProductDetailDescription getDetailDescription() {
        return detailDescription;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(LinkedHashSet<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductGroupContext that = (ProductGroupContext) o;
        return Objects.equals(productGroup, that.productGroup) &&
                Objects.equals(delivery, that.delivery) &&
                Objects.equals(notice, that.notice) &&
                Objects.equals(images, that.images) &&
                Objects.equals(detailDescription, that.detailDescription) &&
                Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroup, delivery, notice, images, detailDescription, products);
    }

    @Override
    public String toString() {
        return "ProductGroupContext{" +
                "productGroup=" + productGroup +
                ", delivery=" + delivery +
                ", notice=" + notice +
                ", images=" + images +
                ", detailDescription=" + detailDescription +
                ", products=" + products +
                '}';
    }

}
