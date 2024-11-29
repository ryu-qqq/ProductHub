package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.brand.BrandQueryService;
import com.ryuqq.setof.domain.core.category.CategoryQueryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupContextAssembler {

    private final ProductFinder productFinder;
    private final ProductGroupConfigContextFinder productGroupConfigContextFinder;
    private final BrandQueryService brandQueryService;
    private final CategoryQueryService categoryQueryService;

    public ProductGroupContextAssembler(ProductFinder productFinder, ProductGroupConfigContextFinder productGroupConfigContextFinder, BrandQueryService brandQueryService, CategoryQueryService categoryQueryService) {
        this.productFinder = productFinder;
        this.productGroupConfigContextFinder = productGroupConfigContextFinder;
        this.brandQueryService = brandQueryService;
        this.categoryQueryService = categoryQueryService;
    }


    public ProductGroupContextAggregate assembleContexts(List<ProductGroup> productGroups) {
        List<Long> brandIds = productGroups.stream().map(ProductGroup::brandId).distinct().toList();
        List<Long> categoryIds = productGroups.stream().map(ProductGroup::categoryId).distinct().toList();
        List<Long> productGroupIds = productGroups.stream().map(ProductGroup::productGroupId).toList();
        List<Long> configIds = productGroups.stream().map(ProductGroup::configId).toList();

        return ProductGroupContextAggregate.of(
                productGroups,
                brandQueryService.fetchBrandsByIds(brandIds),
                categoryQueryService.fetchCategoryRelations(categoryIds, true),
                productFinder.fetchByProductGroupIds(productGroupIds),
                productGroupConfigContextFinder.fetchByConfigIds(configIds)
        );
    }
}
