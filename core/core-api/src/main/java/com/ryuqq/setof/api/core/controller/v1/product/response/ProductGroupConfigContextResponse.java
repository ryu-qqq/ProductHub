package com.ryuqq.setof.api.core.controller.v1.product.response;

import java.util.List;

public record ProductGroupConfigContextResponse(
        ProductGroupConfigResponse productGroupConfig,
        List<ProductGroupNameConfigResponse> productGroupNameConfigs,
        List<ProductGroupDiscountConfigResponse> productGroupDiscountConfigs
) {}
