package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.Origin;

public record ExternalSiteProductPolicy(
        long siteId,
        long policyId,
        Origin countryCode,
        boolean translated
) {

}
