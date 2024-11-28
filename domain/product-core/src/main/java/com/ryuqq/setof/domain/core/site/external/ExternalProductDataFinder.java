package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.brand.MappingBrandQueryService;
import com.ryuqq.setof.domain.core.category.Category;
import com.ryuqq.setof.domain.core.category.MappingCategory;
import com.ryuqq.setof.domain.core.category.MappingCategoryQueryService;
import com.ryuqq.setof.domain.core.product.ProductGroupContext;
import com.ryuqq.setof.domain.core.product.ProductGroupFilter;
import com.ryuqq.setof.domain.core.product.ProductGroupContextQueryService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ExternalProductDataFinder {

    private final ProductGroupContextQueryService productGroupContextQueryService;
    private final ExternalProductQueryService externalProductQueryService;
    private final MappingBrandQueryService mappingBrandQueryService;
    private final MappingCategoryQueryService mappingCategoryQueryService;

    public ExternalProductDataFinder(ProductGroupContextQueryService productGroupContextQueryService, ExternalProductQueryService externalProductQueryService, MappingBrandQueryService mappingBrandQueryService, MappingCategoryQueryService mappingCategoryQueryService) {
        this.productGroupContextQueryService = productGroupContextQueryService;
        this.externalProductQueryService = externalProductQueryService;
        this.mappingBrandQueryService = mappingBrandQueryService;
        this.mappingCategoryQueryService = mappingCategoryQueryService;
    }

    public List<ExternalProductUploadData> findExternalProductUploadData(ExternalProductFilter externalProductFilter) {
        List<ExternalProductContext> externalProductContexts = externalProductQueryService.findExternalProductContext(externalProductFilter);

        List<Long> productGroupIds = externalProductContexts.stream()
                .map(e -> e.externalProductGroup().productGroupId())
                .toList();

        List<ProductGroupContext> productGroups = productGroupContextQueryService.fetchProductGroupContextsByFilter(externalProductFilter.withProductGroupIds(productGroupIds));
        Map<Long, ProductGroupContext> productGroupMap = productGroups.stream()
                .collect(Collectors.toMap(pgc -> pgc.getProductGroup().productGroupId(), p -> p));

        List<Long> brandIds = productGroups.stream()
                .map(p -> p.getProductGroup().brandId())
                .toList();

        List<Long> categoryIds = productGroups.stream()
                .map(p -> p.getProductGroup().categoryId())
                .toList();

        List<MappingBrand> mappingBrands = mappingBrandQueryService.getMappingBrands(externalProductFilter.siteId(), brandIds);
        Map<Long, MappingBrand> mappingBrandMap = mappingBrands.stream()
                .collect(Collectors.toMap(MappingBrand::brandId, Function.identity(), (v1, v2) -> v1));

        List<MappingCategory> mappingCategories = mappingCategoryQueryService.getMappingCategories(externalProductFilter.siteId(), categoryIds);
        Map<Long, MappingCategory> mappingCategoryMap = mappingCategories.stream()
                .collect(Collectors.toMap(MappingCategory::categoryId, Function.identity(), (v1, v2) -> v1));

        return externalProductContexts.stream()
                .map(context -> {
                    ProductGroupContext productGroup = productGroupMap.get(context.externalProductGroup().productGroupId());
                    if (productGroup == null) return null;

                    MappingBrand mappingBrand = mappingBrandMap.get(productGroup.getProductGroup().brandId());
                    Long l = productGroup.getProductGroup().categoryId();
                    MappingCategory mappingCategory = mappingCategoryMap.get(l);

                    return new ExternalProductUploadData(context, productGroup, mappingBrand, mappingCategory);
                })
                .filter(Objects::nonNull)
                .toList();

    }

}
