package com.ryuqq.setof.domain.core.product;


import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.domain.core.category.Category;

import java.util.List;

public class ProductGroupContext {

    private final ProductGroup productGroup;
    private final Brand brand;
    private final List<Category> categories;
    private final List<Product> products;
    private final ProductGroupConfigContext config;

    public ProductGroupContext(ProductGroup productGroup, Brand brand, List<Category> categories, List<Product> products, ProductGroupConfigContext productGroupConfigContext) {
        this.productGroup = productGroup;
        this.brand = brand;
        this.categories = categories;
        this.products = products;
        this.config = productGroupConfigContext;
    }


    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public Brand getBrand() {
        return brand;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public ProductGroupConfigContext getConfig() {
        return config;
    }




}
