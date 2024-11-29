package com.ryuqq.setof.api.core.controller.v1.product.response;

import com.ryuqq.setof.enums.core.ManagementType;
import com.ryuqq.setof.enums.core.OptionType;
import com.ryuqq.setof.enums.core.ProductCondition;
import com.ryuqq.setof.enums.core.ProductStatus;

import java.util.List;

public record ProductGroupResponse(
        long productGroupId,
        long sellerId,
        String productGroupName,
        String styleCode,
        ProductCondition productCondition,
        ManagementType managementType,
        OptionType optionType,
        PriceResponse price,
        boolean soldOutYn,
        boolean displayYn,
        ProductStatus productStatus,
        String keywords,
        ProductDeliveryResponse productDelivery,
        ProductNoticeResponse productNotice,
        ProductDetailDescriptionResponse productDetailDescription,
        List<ProductGroupImageResponse> productImages
) {}









