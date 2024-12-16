package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.category.MappingCategory;

public record ExternalProductGroupContext(
        ExternalProductGroup externalProductGroup,
        MappingBrand mappingBrand,
        MappingCategory mappingCategory

) {
    public long getProductGroupId(){
        return externalProductGroup.productGroupId();
    }
}
