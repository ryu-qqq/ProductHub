package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.category.MappingCategory;
import com.ryuqq.setof.domain.core.product.ProductGroupContext;

import java.util.List;

public record ProductPreExternalSyncAggregate(
        List<ExternalProduct> externalProducts,
        List<ProductGroupContext> productGroupContexts,
        ExternalPolicyContext externalPolicyContext,
        List<MappingCategory> mappingCategories,
        List<MappingBrand> mappingBrands,
        List<ExternalCategoryOption> externalCategoryOptions,
        List<ExternalProductProcessingResult> externalProductProcessingResults
) {

    public static ProductPreExternalSyncAggregate of(
            List<ExternalProduct> externalProducts,
            List<ProductGroupContext> productGroupContexts,
            ExternalPolicyContext externalPolicyContext,
            List<MappingCategory> mappingCategories,
            List<MappingBrand> mappingBrands,
            List<ExternalCategoryOption> externalCategoryOptions,
            List<ExternalProductProcessingResult> externalProductProcessingResults
    ){
        return new ProductPreExternalSyncAggregate(
                externalProducts,
                productGroupContexts,
                externalPolicyContext,
                mappingCategories,
                mappingBrands,
                externalCategoryOptions,
                externalProductProcessingResults
        );
    }
}
