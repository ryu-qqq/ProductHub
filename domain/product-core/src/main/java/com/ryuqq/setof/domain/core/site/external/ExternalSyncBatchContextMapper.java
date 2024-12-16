package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.category.MappingCategory;
import com.ryuqq.setof.domain.core.product.ProductGroupContext;
import com.ryuqq.setof.domain.core.product.gpt.GptOptionsResult;
import com.ryuqq.setof.domain.core.site.StandardSize;
import com.ryuqq.setof.support.utils.JsonUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ExternalSyncBatchContextMapper {

    public ExternalSyncBatchContext toDomain(ProductPreExternalSyncAggregate aggregate) {

        Map<Long, MappingBrand> brandMap = createBrandMap(aggregate);
        Map<Long, MappingCategory> categoryMap = createCategoryMap(aggregate);
        Map<Long, ProductGroupContext> productMap = createProductGroupMap(aggregate);
        Map<String, List<ExternalCategoryOption>> externalCategoryMap = createExternalCategoryMap(aggregate);
        Map<Long, GptOptionsResult> gptOptionsResultMap = createGptOptionsResultMap(aggregate);
        Map<Long, List<StandardSize>> standardSizeMap = createStandardSizeMap(aggregate);

        List<ProductPreExternalSyncContext> productPreExternalSyncContexts = aggregate.externalProductGroups()
                .stream()
                .filter(e -> isValidProduct(e, productMap, brandMap, categoryMap))
                .map(e -> createProductPreExternalSyncContext(
                        e, productMap, brandMap, categoryMap, externalCategoryMap, gptOptionsResultMap, standardSizeMap
                ))
                .toList();

        return new ExternalSyncBatchContext(
                aggregate.externalPolicyContext(),
                productPreExternalSyncContexts
        );
    }

    private Map<Long, MappingBrand> createBrandMap(ProductPreExternalSyncAggregate aggregate) {
        return aggregate.mappingBrands().stream()
                .collect(Collectors.toMap(MappingBrand::brandId, Function.identity(), (v1, v2) -> v2));
    }

    private Map<Long, MappingCategory> createCategoryMap(ProductPreExternalSyncAggregate aggregate) {
        return aggregate.mappingCategories().stream()
                .collect(Collectors.toMap(MappingCategory::categoryId, Function.identity(), (v1, v2) -> v2));
    }

    private Map<Long, ProductGroupContext> createProductGroupMap(ProductPreExternalSyncAggregate aggregate) {
        return aggregate.productGroupContexts().stream()
                .collect(Collectors.toMap(
                        p -> p.getProductGroup().productGroupId(),
                        Function.identity(),
                        (v1, v2) -> v2
                ));
    }

    private Map<String, List<ExternalCategoryOption>> createExternalCategoryMap(ProductPreExternalSyncAggregate aggregate) {
        return aggregate.externalCategoryOptions().stream()
                .collect(Collectors.groupingBy(ExternalCategoryOption::externalCategoryId));
    }

    private Map<Long, GptOptionsResult> createGptOptionsResultMap(ProductPreExternalSyncAggregate aggregate) {
        return aggregate.externalProductProcessingResults().stream()
                .map(result -> JsonUtils.fromJson(result.result(), GptOptionsResult.class))
                .collect(Collectors.toMap(GptOptionsResult::productGroupId, Function.identity(), (v1, v2) -> v2));
    }

    private Map<Long, List<StandardSize>> createStandardSizeMap(ProductPreExternalSyncAggregate aggregate) {
        return aggregate.standardSizes().stream()
                .collect(Collectors.groupingBy(StandardSize::categoryId));
    }

    private boolean isValidProduct(ExternalProductGroup product, Map<Long, ProductGroupContext> productMap,
                                   Map<Long, MappingBrand> brandMap, Map<Long, MappingCategory> categoryMap) {
        return productMap.containsKey(product.productGroupId())
                && brandMap.containsKey(product.internalBrandId())
                && categoryMap.containsKey(product.internalCategoryId());
    }

    private ProductPreExternalSyncContext createProductPreExternalSyncContext(
            ExternalProductGroup externalProductGroup,
            Map<Long, ProductGroupContext> productMap,
            Map<Long, MappingBrand> brandMap,
            Map<Long, MappingCategory> categoryMap,
            Map<String, List<ExternalCategoryOption>> externalCategoryMap,
            Map<Long, GptOptionsResult> gptOptionsResultMap,
            Map<Long, List<StandardSize>> standardSizeMap) {

        MappingCategory mappingCategory = categoryMap.get(externalProductGroup.internalCategoryId());
        if (mappingCategory == null) {
            mappingCategory = findMappingCategoryByPath(externalProductGroup, categoryMap);
        }

        List<ExternalCategoryOption> externalCategoryOptions = externalCategoryMap.getOrDefault(
                externalProductGroup.externalCategoryId(), externalCategoryMap.getOrDefault(externalProductGroup.externalExtraCategoryId(), List.of()));

        if (externalCategoryOptions.isEmpty()) {
            externalCategoryOptions = findExternalCategoryOptionsByPath(
                    externalProductGroup, categoryMap, externalCategoryMap);
        }

        return new ProductPreExternalSyncContext(
                productMap.get(externalProductGroup.productGroupId()),
                externalProductGroup,
                brandMap.get(externalProductGroup.internalBrandId()),
                mappingCategory,
                externalCategoryOptions,
                gptOptionsResultMap.get(externalProductGroup.productGroupId()),
                getStandardSize(standardSizeMap, externalProductGroup)
        );
    }

    private MappingCategory findMappingCategoryByPath(ExternalProductGroup externalProductGroup, Map<Long, MappingCategory> categoryMap) {
        return externalProductGroup.categoryPath().stream()
                .filter(categoryMap::containsKey)
                .findFirst()
                .map(categoryMap::get)
                .orElse(null);
    }


    private List<ExternalCategoryOption> findExternalCategoryOptionsByPath(
            ExternalProductGroup externalProductGroup,
            Map<Long, MappingCategory> categoryMap,
            Map<String, List<ExternalCategoryOption>> externalCategoryMap) {

        return externalProductGroup.categoryPath().stream()
                .map(categoryMap::get)
                .filter(Objects::nonNull)
                .map(MappingCategory::externalCategoryId)
                .flatMap(externalCategoryId -> externalCategoryMap.getOrDefault(externalCategoryId, List.of()).stream())
                .toList();
    }


    private List<StandardSize> getStandardSize(Map<Long, List<StandardSize>> standardSizeMap, ExternalProductGroup externalProductGroup){
        return externalProductGroup.categoryPath().stream()
                .map(aLong -> standardSizeMap.getOrDefault(aLong, new ArrayList<>()))
                .flatMap(Collection::stream)
                .toList();
    }
}
