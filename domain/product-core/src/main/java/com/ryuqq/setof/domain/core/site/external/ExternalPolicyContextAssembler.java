package com.ryuqq.setof.domain.core.site.external;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalPolicyContextAssembler {

    private final ExternalProductPolicyFinder externalProductPolicyFinder;


    public ExternalPolicyContextAssembler(ExternalProductPolicyFinder externalProductPolicyFinder) {
        this.externalProductPolicyFinder = externalProductPolicyFinder;
    }

    public ExternalPolicyContextAggregate assemble(List<ExternalPolicy> externalPolicies){
        List<Long> siteIds = externalPolicies.stream().map(ExternalPolicy::siteId).toList();
        List<ExternalProductPolicy> externalProductPolicies = externalProductPolicyFinder.fetchByIds(siteIds);


        return ExternalPolicyContextAggregate.of(
                externalPolicies,
                externalProductPolicies,
                List.of()
        );
    }
}
