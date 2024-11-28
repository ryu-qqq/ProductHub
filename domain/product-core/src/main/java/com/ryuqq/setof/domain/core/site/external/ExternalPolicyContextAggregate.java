package com.ryuqq.setof.domain.core.site.external;

import java.util.List;

public record ExternalPolicyContextAggregate(
        List<ExternalPolicy> externalPolicies,
        List<ExternalProductPolicy> externalProductPolicies,
        List<ExternalPricePolicy> externalPricePolicies
) {

    public static ExternalPolicyContextAggregate of(List<ExternalPolicy> externalPolicies,
                                      List<ExternalProductPolicy> externalProductPolicies,
                                      List<ExternalPricePolicy> externalPricePolicies){

        return new ExternalPolicyContextAggregate(
                externalPolicies,
                externalProductPolicies,
                externalPricePolicies
        );
    }

}
