package com.ryuqq.setof.batch.core.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.brand.MappingBrandQueryService;
import com.ryuqq.setof.domain.core.category.Category;
import com.ryuqq.setof.domain.core.category.MappingCategory;
import com.ryuqq.setof.domain.core.category.MappingCategoryQueryService;
import com.ryuqq.setof.domain.core.product.ProductGroupContext;
import com.ryuqq.setof.domain.core.product.ProductGroupFilter;
import com.ryuqq.setof.domain.core.product.ProductGroupContextQueryService;
import com.ryuqq.setof.domain.core.site.external.ExternalProductContext;
import com.ryuqq.setof.domain.core.site.external.ExternalProductQueryService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExternalProductIntegrationReader {

    private final ProductGroupContextQueryService productGroupContextQueryService;
    private final ExternalProductQueryService externalProductQueryService;
    private final MappingBrandQueryService mappingBrandQueryService;
    private final MappingCategoryQueryService mappingCategoryQueryService;

    public ExternalProductIntegrationReader(ProductGroupContextQueryService productGroupContextQueryService, ExternalProductQueryService externalProductQueryService, MappingBrandQueryService mappingBrandQueryService, MappingCategoryQueryService mappingCategoryQueryService) {
        this.productGroupContextQueryService = productGroupContextQueryService;
        this.externalProductQueryService = externalProductQueryService;
        this.mappingBrandQueryService = mappingBrandQueryService;
        this.mappingCategoryQueryService = mappingCategoryQueryService;
    }


    public void read() {
//        long siteId= 4L;
//        List<ExternalProductContext> externalProductContexts = externalProductQueryService.findExternalProductContext(null);
//        List<Long> productGroupIds = externalProductContexts.stream()
//                .map(e -> e.externalProductGroup().productGroupId())
//                .toList();
//
//        List<ProductGroupContext> productGroups = productGroupContextQueryService.findProductGroupContexts(ProductGroupFilter.of(productGroupIds), List.of());
//        Map<Long, ProductGroupContext> productGroupMap = productGroups.stream()
//                .collect(Collectors.toMap(ProductGroupContext::getProductGroupId, p -> p));
//
//        List<Long> brandIds = productGroups.stream()
//                .map(p -> p.getProductGroup().getBrand().id())
//                .toList();
//
//        List<Long> categoryIds = productGroups.stream()
//                .flatMap(p -> p.getProductGroup().getCategories().stream())
//                .map(Category::id)
//                .toList();
//
//        List<MappingBrand> mappingBrands = mappingBrandQueryService.getMappingBrands(siteId, brandIds);
//        Map<Long, MappingBrand> mappingBrandMap = mappingBrands.stream()
//                .collect(Collectors.toMap(MappingBrand::brandId, b -> b));
//
//        List<MappingCategory> mappingCategories = mappingCategoryQueryService.getMappingCategories(siteId, categoryIds);
//        Map<Long, MappingCategory> mappingCategoryMap = mappingCategories.stream()
//                .collect(Collectors.toMap(MappingCategory::categoryId, c -> c));


//        return externalProductContexts.stream()
//                .map(context -> {
//                    ProductGroupContext productGroup = productGroupMap.get(context.externalProductGroup().productGroupId());
//                    if (productGroup == null) return null;
//
//                    MappingBrand mappingBrand = mappingBrandMap.get(productGroup.getProductGroup().getBrand().id());
//                    List<MappingCategory> mappedCategories = productGroup.getProductGroup().getCategories().stream()
//                            .map(category -> mappingCategoryMap.get(category.id()))
//                            .filter(Objects::nonNull)
//                            .toList();
//
//                    return new ExternalProductUploadData(context, productGroup, mappingBrand, mappedCategories.isEmpty() ? null : mappedCategories.get(0));
//                })
//                .filter(Objects::nonNull)
//                .toList();
    }


}
