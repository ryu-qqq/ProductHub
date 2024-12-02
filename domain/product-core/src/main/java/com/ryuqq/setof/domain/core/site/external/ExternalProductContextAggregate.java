package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.category.MappingCategory;

import java.util.List;

public record ExternalProductContextAggregate(
        List<ExternalProduct> externalProducts,
        List<MappingBrand> mappingBrands,
        List<MappingCategory> mappingCategories,
        ExternalPolicyContext externalPolicyContext
) {
    public static ExternalProductContextAggregate of(
            List<ExternalProduct> externalProducts,
            List<MappingBrand> mappingBrands,
            List<MappingCategory> mappingCategories,
            ExternalPolicyContext externalPolicyContext
    ) {
        return new ExternalProductContextAggregate(externalProducts, mappingBrands, mappingCategories, externalPolicyContext);
    }

}
