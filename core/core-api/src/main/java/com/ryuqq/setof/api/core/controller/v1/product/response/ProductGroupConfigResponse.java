package com.ryuqq.setof.api.core.controller.v1.product.response;

public record ProductGroupConfigResponse(
        long configId,
        long productGroupId,
        boolean activeYn
) {
}
