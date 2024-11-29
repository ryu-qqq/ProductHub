package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.Origin;

public record ProductGroupNameConfig(
        long productGroupNameConfigId,
        long configId,
        long productGroupId,
        Origin countryCode,
        String customName
) {}
