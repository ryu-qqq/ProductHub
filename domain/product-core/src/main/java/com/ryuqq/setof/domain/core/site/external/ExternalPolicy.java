package com.ryuqq.setof.domain.core.site.external;

public record ExternalPolicy(
        long siteId,
        long policyId,
        String name,
        String description,
        boolean activeYn
) {}
