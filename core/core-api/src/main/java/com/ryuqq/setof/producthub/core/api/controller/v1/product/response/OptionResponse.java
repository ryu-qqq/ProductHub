package com.ryuqq.setof.producthub.core.api.controller.v1.product.response;

import com.ryuqq.setof.core.OptionName;

public record OptionResponse(
        Long productId,
        Long optionGroupId,
        Long optionDetailId,
        OptionName optionName,
        String optionValue
) {}
