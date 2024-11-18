package com.ryuqq.setof.api.core.controller.v1.product.response;

import com.ryuqq.setof.enums.core.OptionName;

public record OptionResponse(
        Long productId,
        Long optionGroupId,
        Long optionDetailId,
        OptionName optionName,
        String optionValue
) {}
