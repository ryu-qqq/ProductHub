package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.category.MappingCategory;

import java.util.List;

public record ExternalProductGroupContextAggregate(
        List<ExternalProductGroup> externalProductGroups,
        List<MappingBrand> mappingBrands,
        List<MappingCategory> mappingCategories,
        ExternalPolicyContext externalPolicyContext
) {
    public static ExternalProductGroupContextAggregate of(
            List<ExternalProductGroup> externalProductGroups,
            List<MappingBrand> mappingBrands,
            List<MappingCategory> mappingCategories,
            ExternalPolicyContext externalPolicyContext
    ) {
        return new ExternalProductGroupContextAggregate(externalProductGroups, mappingBrands, mappingCategories, externalPolicyContext);
    }

}
