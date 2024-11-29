package com.ryuqq.setof.domain.core.site.external;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ExternalPolicyContextMapper {

    public List<ExternalPolicyContext> toDomains(ExternalPolicyContextAggregate externalPolicyContextAggregate){
        Map<Long, ExternalProductPolicy> externalProductPolicyMap = externalPolicyContextAggregate.externalProductPolicies().stream()
                .collect(Collectors.toMap(ExternalProductPolicy::siteId, Function.identity(), (v1, v2) -> v2));

        return externalPolicyContextAggregate.externalPolicies().stream()
                .map(policy -> new ExternalPolicyContext(
                        policy,
                        externalProductPolicyMap.get(policy.siteId()),
                        null
                ))
                .toList();
    }


}
