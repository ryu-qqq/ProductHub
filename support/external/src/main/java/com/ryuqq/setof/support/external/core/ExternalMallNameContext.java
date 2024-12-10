package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.Origin;

public record ExternalMallNameContext(
        String name,
        String description,
        long productGroupId,
        String styleCode,
        Origin countryCode
) {}
