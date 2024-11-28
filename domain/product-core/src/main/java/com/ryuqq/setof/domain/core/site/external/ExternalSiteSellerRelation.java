package com.ryuqq.setof.domain.core.site.external;

import java.util.List;

public record ExternalSiteSellerRelation(

        long sellerId,
        List<ExternalSiteProductPolicy> externalSiteProductPolicies
) {}
