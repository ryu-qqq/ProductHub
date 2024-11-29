package com.ryuqq.setof.api.core.controller.v1.product.response;

import com.ryuqq.setof.enums.core.Origin;

public record ProductGroupNameConfigResponse(
        Origin countryCode,
        String customName
) {}
