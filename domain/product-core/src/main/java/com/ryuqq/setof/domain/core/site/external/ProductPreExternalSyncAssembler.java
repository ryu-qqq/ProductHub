package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrandQueryService;
import com.ryuqq.setof.domain.core.category.MappingCategoryQueryService;
import com.ryuqq.setof.domain.core.product.ProductGroupContextQueryService;
import com.ryuqq.setof.domain.core.product.ProductGroupFilter;
import com.ryuqq.setof.enums.core.ProductDataType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductPreExternalSyncAssembler {

    private final ProductGroupContextQueryService productGroupContextQueryService;
    private final MappingBrandQueryService mappingBrandQueryService;
    private final MappingCategoryQueryService mappingCategoryQueryService;
    private final ExternalPolicyContextQueryService externalPolicyContextQueryService;
    private final ExternalCategoryOptionFinder externalCategoryOptionFinder;
    private final ExternalProductProcessingResultQueryService externalProductProcessingResultQueryService;

    public ProductPreExternalSyncAssembler(ProductGroupContextQueryService productGroupContextQueryService, MappingBrandQueryService mappingBrandQueryService, MappingCategoryQueryService mappingCategoryQueryService, ExternalPolicyContextQueryService externalPolicyContextQueryService, ExternalCategoryOptionFinder externalCategoryOptionFinder, ExternalProductProcessingResultQueryService externalProductProcessingResultQueryService) {
        this.productGroupContextQueryService = productGroupContextQueryService;
        this.mappingBrandQueryService = mappingBrandQueryService;
        this.mappingCategoryQueryService = mappingCategoryQueryService;
        this.externalPolicyContextQueryService = externalPolicyContextQueryService;
        this.externalCategoryOptionFinder = externalCategoryOptionFinder;
        this.externalProductProcessingResultQueryService = externalProductProcessingResultQueryService;
    }

    public ProductPreExternalSyncAggregate assemble(long siteId, List<ExternalProduct> externalProducts){
        List<Long> brandIds = externalProducts.stream().map(ExternalProduct::internalBrandId).toList();
        List<Long> categoryIds = externalProducts.stream().map(ExternalProduct::internalCategoryId).toList();
        List<Long> productGroupIds = externalProducts.stream().map(ExternalProduct::productGroupId).toList();

        return ProductPreExternalSyncAggregate.of(
                externalProducts,
                productGroupContextQueryService.fetchProductGroupContextsByFilter(ProductGroupFilter.of(productGroupIds)),
                externalPolicyContextQueryService.fetchById(siteId),
                mappingCategoryQueryService.fetchBySiteIdAndCategoryIds(siteId, categoryIds),
                mappingBrandQueryService.fetchBySiteIdAndBrandIds(siteId, brandIds),
                externalCategoryOptionFinder.fetchBySiteIdAndCategoryIds(siteId, categoryIds),
                externalProductProcessingResultQueryService.fetchByProductGroupIdsAndDataType(productGroupIds, ProductDataType.OPTIONS)
        );
    }




}
