package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.category.MappingCategory;
import com.ryuqq.setof.domain.core.product.ProductGroupContext;
import com.ryuqq.setof.domain.core.product.gpt.GptOptionsResult;

import java.util.List;

public record ProductPreExternalSyncContext(
        ProductGroupContext productGroupContext,
        ExternalProduct externalProduct,
        MappingBrand mappingBrand,
        MappingCategory mappingCategory,
        List<ExternalCategoryOption> externalCategoryOptions,
        GptOptionsResult gptOptionsResult
) {

    public long getProductGroupId(){
        return productGroupContext.getProductGroup().productGroupId();
    }

}
