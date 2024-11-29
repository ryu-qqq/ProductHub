package com.ryuqq.setof.domain.core.site.external;

public record ExternalPolicy(
        long siteId,
        String siteName,
        long policyId,
        String name,
        String description,
        boolean activeYn
) {}
