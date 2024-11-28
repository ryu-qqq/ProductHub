package com.ryuqq.setof.domain.core.site.external;

import java.util.List;

public record ExternalPolicyContext(
        ExternalPolicy externalPolicy,
        ExternalProductPolicy productPolicy,
        List<ExternalPricePolicy> pricePolicies
) {

    public long getPolicyId() {
        return externalPolicy.policyId();
    }

    public long getSiteId() {
        return externalPolicy.siteId();
    }

}
