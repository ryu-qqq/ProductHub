package com.ryuqq.setof.producthub.core.api.controller.v1.product.response;

import com.ryuqq.setof.core.ManagementType;
import com.ryuqq.setof.core.OptionType;
import com.ryuqq.setof.core.ProductCondition;
import com.ryuqq.setof.core.ProductStatus;
import com.ryuqq.setof.domain.core.brand.BrandRecord;
import com.ryuqq.setof.domain.core.category.CategoryRecord;
import com.ryuqq.setof.domain.core.product.Color;

import java.util.List;

public record ProductGroupResponse(
        long productGroupId,
        long sellerId,
        Color color,
        List<CategoryRecord> categories,
        BrandRecord brand,
        String productGroupName,
        String styleCode,
        ProductCondition productCondition,
        ManagementType managementType,
        OptionType optionType,
        PriceResponse price,
        boolean soldOutYn,
        boolean displayYn,
        ProductStatus productStatus
) {}









