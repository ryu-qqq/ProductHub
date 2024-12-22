package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.exception.NotFoundException;
import com.ryuqq.setof.domain.core.product.ExternalPolicyMapper;
import com.ryuqq.setof.db.core.site.external.ExternalPolicyQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalPolicyFinder {

    private final ExternalPolicyMapper externalPolicyMapper;
    private final ExternalPolicyQueryRepository externalPolicyQueryRepository;

    public ExternalPolicyFinder(ExternalPolicyMapper externalPolicyMapper, ExternalPolicyQueryRepository externalPolicyQueryRepository) {
        this.externalPolicyMapper = externalPolicyMapper;
        this.externalPolicyQueryRepository = externalPolicyQueryRepository;
    }

    public ExternalPolicy fetchById(Long siteId) {
        return externalPolicyQueryRepository.fetchBySiteId(List.of(siteId))
                .stream()
                .map(externalPolicyMapper::toDomain)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format(ExternalSiteErrorConstants.EXTERNAL_SITE_POLICY_NOT_FOUND_ERROR_MSG, siteId)));
    }


    public List<ExternalPolicy> fetchByIds(List<Long> siteIds) {
        return externalPolicyQueryRepository.fetchBySiteId(siteIds).stream()
                .map(externalPolicyMapper::toDomain)
                .toList();
    }
}
