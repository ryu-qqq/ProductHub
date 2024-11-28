package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.ExternalProductPolicyQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalProductPolicyFinder {

    private final ExternalProductPolicyQueryRepository externalProductPolicyQueryRepository;

    public ExternalProductPolicyFinder(ExternalProductPolicyQueryRepository externalProductPolicyQueryRepository) {
        this.externalProductPolicyQueryRepository = externalProductPolicyQueryRepository;
    }

    public List<ExternalProductPolicy> fetchByIds(List<Long> siteIds) {
        return externalProductPolicyQueryRepository.fetchByFilter(siteIds).stream()
                .map(e -> new ExternalProductPolicy(
                        e.getPolicyId(),
                        e.getSiteId(),
                        e.getCountryCode(),
                        e.isTranslated()
                ))
                .toList();
    }

}
