package com.ryuqq.setof.api.core.controller.v1.product.request;

import com.ryuqq.setof.enums.core.ProductStatus;

import java.util.List;

public record ProductStatusUpdateRequestDto(
        List<Long> productGroupIds,
        ProductStatus productStatus
) {}
