package com.ryuqq.setof.api.core.controller.v1.product.response;

import com.ryuqq.setof.domain.core.brand.BrandRecord;
import com.ryuqq.setof.domain.core.category.CategoryRecord;
import com.ryuqq.setof.domain.core.product.Color;
import com.ryuqq.setof.enums.core.ManagementType;
import com.ryuqq.setof.enums.core.OptionType;
import com.ryuqq.setof.enums.core.ProductCondition;
import com.ryuqq.setof.enums.core.ProductStatus;

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
        ProductStatus productStatus,
        String keywords
) {}









