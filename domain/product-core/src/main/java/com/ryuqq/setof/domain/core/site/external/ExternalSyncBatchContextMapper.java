package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.category.MappingCategory;
import com.ryuqq.setof.domain.core.product.ProductGroupContext;
import com.ryuqq.setof.domain.core.product.gpt.GptOptionsResult;
import com.ryuqq.setof.support.utils.JsonUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ExternalSyncBatchContextMapper {

    public ExternalSyncBatchContext toDomain(ProductPreExternalSyncAggregate aggregate){

        Map<Long, MappingBrand> brandMap = aggregate.mappingBrands().stream()
                .collect(Collectors.toMap(MappingBrand::brandId, Function.identity(), (v1, v2) -> v2));

        Map<Long, MappingCategory> categoryMap = aggregate.mappingCategories().stream()
                .collect(Collectors.toMap(MappingCategory::categoryId, Function.identity(), (v1, v2) -> v2));

        Map<Long, ProductGroupContext> productMap = aggregate.productGroupContexts().stream()
                .collect(Collectors.toMap(p -> p.getProductGroup().productGroupId(), Function.identity(), (v1, v2) -> v2));

        Map<String, List<ExternalCategoryOption>> externalCategoryMap = aggregate.externalCategoryOptions().stream()
                .collect(Collectors.groupingBy(ExternalCategoryOption::externalCategoryId));

        Map<Long, GptOptionsResult> gptOptionsResultMap = aggregate.externalProductProcessingResults().stream()
                .map(externalProductProcessingResult -> JsonUtils.fromJson(externalProductProcessingResult.result(), GptOptionsResult.class))
                .collect(Collectors.toMap(GptOptionsResult::productGroupId, Function.identity(), (v1, v2) -> v2));


        List<ExternalProduct> unmappedProducts = aggregate.externalProducts()
                .stream()
                .filter(e -> !productMap.containsKey(e.productGroupId())
                        || !brandMap.containsKey(e.internalBrandId())
                        || !categoryMap.containsKey(e.internalCategoryId()))
                .toList();


        List<ProductPreExternalSyncContext> productPreExternalSyncContexts = aggregate.externalProducts()
                .stream()
                .filter(e -> productMap.containsKey(e.productGroupId())
                        && brandMap.containsKey(e.internalBrandId())
                        && categoryMap.containsKey(e.internalCategoryId()))
                .map(e ->


                        new ProductPreExternalSyncContext(
                        productMap.get(e.productGroupId()),
                        e,
                        brandMap.get(e.internalBrandId()),
                        categoryMap.get(e.internalCategoryId()),
                        externalCategoryMap.getOrDefault(e.externalCategoryId(), List.of()),
                        gptOptionsResultMap.get(e.productGroupId())
                ))
                .toList();

        return new ExternalSyncBatchContext(
                aggregate.externalPolicyContext(),
                productPreExternalSyncContexts,
                unmappedProducts
        );
    }

}
