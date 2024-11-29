package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalPolicyContextFinder implements ExternalPolicyContextQueryService {

    private final ExternalPolicyFinder externalPolicyFinder;
    private final ExternalPolicyContextAssembler assembler;
    private final ExternalPolicyContextMapper externalPolicyContextMapper;

    public ExternalPolicyContextFinder(ExternalPolicyFinder externalPolicyFinder, ExternalPolicyContextAssembler assembler, ExternalPolicyContextMapper externalPolicyContextMapper) {
        this.externalPolicyFinder = externalPolicyFinder;
        this.assembler = assembler;
        this.externalPolicyContextMapper = externalPolicyContextMapper;
    }

    @Override
    public ExternalPolicyContext fetchById(long siteId){
        ExternalPolicy externalPolicy = externalPolicyFinder.fetchById(siteId);
        ExternalPolicyContextAggregate externalPolicyContextAggregate = assembler.assemble(List.of(externalPolicy));

        return externalPolicyContextMapper.toDomains(externalPolicyContextAggregate)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format(ExternalSiteErrorConstants.EXTERNAL_SITE_POLICY_NOT_FOUND_ERROR_MSG, siteId)));
    }

    @Override
    public List<ExternalPolicyContext> fetchExternalPolicies(List<Long> siteIds){
        List<ExternalPolicy> externalPolicies = externalPolicyFinder.fetchByIds(siteIds);
        if(externalPolicies.isEmpty()) return List.of();
        ExternalPolicyContextAggregate externalPolicyContextAggregate = assembler.assemble(externalPolicies);

        return externalPolicyContextMapper.toDomains(externalPolicyContextAggregate);
    }


}
