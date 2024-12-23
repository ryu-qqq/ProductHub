package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrandQueryService;
import com.ryuqq.setof.domain.core.category.MappingCategoryQueryService;
import com.ryuqq.setof.domain.core.product.ProductGroupContextQueryService;
import com.ryuqq.setof.domain.core.product.ProductGroupFilter;
import com.ryuqq.setof.domain.core.site.StandardSizeQueryService;
import com.ryuqq.setof.enums.core.ProductDataType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductPreExternalSyncAssembler {

    private final ProductGroupContextQueryService productGroupContextQueryService;
    private final MappingBrandQueryService mappingBrandQueryService;
    private final MappingCategoryQueryService mappingCategoryQueryService;
    private final ExternalPolicyContextQueryService externalPolicyContextQueryService;
    private final ExternalCategoryOptionFinder externalCategoryOptionFinder;
    private final ExternalProductProcessingResultQueryService externalProductProcessingResultQueryService;
    private final StandardSizeQueryService standardSizeQueryService;

    public ProductPreExternalSyncAssembler(ProductGroupContextQueryService productGroupContextQueryService, MappingBrandQueryService mappingBrandQueryService, MappingCategoryQueryService mappingCategoryQueryService, ExternalPolicyContextQueryService externalPolicyContextQueryService, ExternalCategoryOptionFinder externalCategoryOptionFinder, ExternalProductProcessingResultQueryService externalProductProcessingResultQueryService, StandardSizeQueryService standardSizeQueryService) {
        this.productGroupContextQueryService = productGroupContextQueryService;
        this.mappingBrandQueryService = mappingBrandQueryService;
        this.mappingCategoryQueryService = mappingCategoryQueryService;
        this.externalPolicyContextQueryService = externalPolicyContextQueryService;
        this.externalCategoryOptionFinder = externalCategoryOptionFinder;
        this.externalProductProcessingResultQueryService = externalProductProcessingResultQueryService;
        this.standardSizeQueryService = standardSizeQueryService;
    }

    public ProductPreExternalSyncAggregate assemble(long siteId, List<ExternalProductGroup> externalProductGroups){
        List<Long> brandIds = externalProductGroups.stream().map(ExternalProductGroup::internalBrandId).toList();
        Set<Long> categoryIds = externalProductGroups.stream().map(ExternalProductGroup::internalCategoryId).collect(Collectors.toSet());
        Set<Long> categoryPathIds = externalProductGroups.stream()
                .flatMap(product -> product.categoryPath().stream())
                .collect(Collectors.toSet());

        categoryIds.addAll(categoryPathIds);
        ArrayList<Long> categoryIdList = new ArrayList<>(categoryIds);

        List<Long> productGroupIds = externalProductGroups.stream().map(ExternalProductGroup::productGroupId).toList();

        return ProductPreExternalSyncAggregate.of(
                externalProductGroups,
                productGroupContextQueryService.fetchProductGroupContextsByFilter(ProductGroupFilter.of(productGroupIds)),
                externalPolicyContextQueryService.fetchById(siteId),
                mappingCategoryQueryService.fetchBySiteIdAndCategoryIds(siteId, categoryIdList),
                mappingBrandQueryService.fetchBySiteIdAndBrandIds(siteId, brandIds),
                externalCategoryOptionFinder.fetchBySiteIdAndCategoryIds(siteId, categoryIdList),
                externalProductProcessingResultQueryService.fetchByProductGroupIdsAndDataType(productGroupIds, ProductDataType.OPTIONS),
                standardSizeQueryService.fetchByCategoryIds(categoryIdList)
        );
    }




}
