package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.brand.MappingBrandQueryService;
import com.ryuqq.setof.domain.core.category.MappingCategory;
import com.ryuqq.setof.domain.core.category.MappingCategoryQueryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalProductContextAssembler {

    private final ExternalPolicyContextQueryService externalPolicyContextQueryService;
    private final MappingBrandQueryService mappingBrandQueryService;
    private final MappingCategoryQueryService mappingCategoryQueryService;

    public ExternalProductContextAssembler(ExternalPolicyContextQueryService externalPolicyContextQueryService, MappingBrandQueryService mappingBrandQueryService, MappingCategoryQueryService mappingCategoryQueryService) {
        this.externalPolicyContextQueryService = externalPolicyContextQueryService;
        this.mappingBrandQueryService = mappingBrandQueryService;
        this.mappingCategoryQueryService = mappingCategoryQueryService;
    }

    public ExternalProductContextAggregate assembleContexts(long siteId, List<ExternalProduct> externalProducts){
        List<Long> categoryIds = externalProducts.stream().map(ExternalProduct::internalCategoryId).toList();
        List<Long> brandIds = externalProducts.stream().map(ExternalProduct::internalBrandId).toList();

        ExternalPolicyContext externalPolicyContext = externalPolicyContextQueryService.fetchById(siteId);
        List<MappingCategory> mappingCategories = mappingCategoryQueryService.fetchBySiteIdAndCategoryIds(siteId, categoryIds);
        List<MappingBrand> mappingBrands = mappingBrandQueryService.fetchBySiteIdAndBrandIds(siteId, brandIds);

        return ExternalProductContextAggregate.of(
                externalProducts,
                mappingBrands,
                mappingCategories,
                externalPolicyContext
        );
    }


}
