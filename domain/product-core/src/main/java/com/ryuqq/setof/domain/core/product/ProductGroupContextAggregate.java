package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.domain.core.category.CategoryRelation;

import java.util.List;

public record ProductGroupContextAggregate(
        List<ProductGroup> productGroups,
        List<Brand> brands,
        List<CategoryRelation> categories,
        List<Product> products,
        List<ProductGroupConfigContext> configs
) {
    public static ProductGroupContextAggregate of(
            List<ProductGroup> productGroups,
            List<Brand> brands,
            List<CategoryRelation> categories,
            List<Product> products,
            List<ProductGroupConfigContext> configs
    ) {
        return new ProductGroupContextAggregate(productGroups, brands, categories, products, configs);
    }
}