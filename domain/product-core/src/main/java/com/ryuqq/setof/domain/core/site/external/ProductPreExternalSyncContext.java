package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.category.MappingCategory;
import com.ryuqq.setof.domain.core.product.ProductGroupContext;
import com.ryuqq.setof.domain.core.product.gpt.GptOptionsResult;
import com.ryuqq.setof.domain.core.site.StandardSize;

import java.util.List;

public record ProductPreExternalSyncContext(
        ProductGroupContext productGroupContext,
        ExternalProductGroup externalProductGroup,
        MappingBrand mappingBrand,
        MappingCategory mappingCategory,
        List<ExternalCategoryOption> externalCategoryOptions,
        GptOptionsResult gptOptionsResult,
        List<StandardSize> standardSizes
) {

    public long getProductGroupId(){
        return productGroupContext.getProductGroup().productGroupId();
    }

}
