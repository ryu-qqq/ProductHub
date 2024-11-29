package com.ryuqq.setof.api.core.controller.v1.site.response;

import com.ryuqq.setof.enums.core.Origin;

public record ExternalProductPolicyResponse(
        long policyId,
        Origin countryCode,
        boolean translatedNeeded
) {}
