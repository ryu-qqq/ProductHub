package com.ryuqq.setof.producthub.core.api.controller.v1.product.response;

import java.util.List;

public record ProductGroupInsertResponseDto(
        long productGroupId,
        List<Long>productIds
) {}
