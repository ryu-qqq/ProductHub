package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.brand.MappingBrandQueryService;
import com.ryuqq.setof.domain.core.category.MappingCategory;
import com.ryuqq.setof.domain.core.category.MappingCategoryQueryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalProductGroupContextAssembler {

    private final ExternalPolicyContextQueryService externalPolicyContextQueryService;
    private final MappingBrandQueryService mappingBrandQueryService;
    private final MappingCategoryQueryService mappingCategoryQueryService;

    public ExternalProductGroupContextAssembler(ExternalPolicyContextQueryService externalPolicyContextQueryService, MappingBrandQueryService mappingBrandQueryService, MappingCategoryQueryService mappingCategoryQueryService) {
        this.externalPolicyContextQueryService = externalPolicyContextQueryService;
        this.mappingBrandQueryService = mappingBrandQueryService;
        this.mappingCategoryQueryService = mappingCategoryQueryService;
    }

    public ExternalProductGroupContextAggregate assembleContexts(long siteId, List<ExternalProductGroup> externalProductGroups){
        List<Long> categoryIds = externalProductGroups.stream().map(ExternalProductGroup::internalCategoryId).toList();
        List<Long> brandIds = externalProductGroups.stream().map(ExternalProductGroup::internalBrandId).toList();

        ExternalPolicyContext externalPolicyContext = externalPolicyContextQueryService.fetchById(siteId);
        List<MappingCategory> mappingCategories = mappingCategoryQueryService.fetchBySiteIdAndCategoryIds(siteId, categoryIds);
        List<MappingBrand> mappingBrands = mappingBrandQueryService.fetchBySiteIdAndBrandIds(siteId, brandIds);

        return ExternalProductGroupContextAggregate.of(
                externalProductGroups,
                mappingBrands,
                mappingCategories,
                externalPolicyContext
        );

    }


}
