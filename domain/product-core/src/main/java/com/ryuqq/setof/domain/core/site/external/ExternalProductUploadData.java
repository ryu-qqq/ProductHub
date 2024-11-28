package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.category.MappingCategory;
import com.ryuqq.setof.domain.core.product.ProductGroupContext;

public record ExternalProductUploadData(
        ExternalProductContext externalProductContext,
        ProductGroupContext productGroupContext,
        MappingBrand mappingBrand,
        MappingCategory mappingCategory
) {}
