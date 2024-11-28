package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.ExternalSiteQueryRepository;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductPolicyDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalSiteSellerRelationDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalSiteSellerRelationFinder {

    private final ExternalSiteQueryRepository externalSiteQueryRepository;

    public ExternalSiteSellerRelationFinder(ExternalSiteQueryRepository externalSiteQueryRepository) {
        this.externalSiteQueryRepository = externalSiteQueryRepository;
    }

    public List<ExternalSiteSellerRelation> findExternalSiteSellerRelation(List<Long> sellerIds){
        List<ExternalSiteSellerRelationDto> externalSiteSellerRelationDtos = externalSiteQueryRepository.fetchBySellerId(sellerIds);
        return externalSiteSellerRelationDtos.stream()
                .map(e ->
                        new ExternalSiteSellerRelation(e.getSellerId(), toExternalSiteProductPolicies(e.getExternalProductPolicyDtos()))
                )
                .toList();
    }

    private List<ExternalSiteProductPolicy> toExternalSiteProductPolicies(List<ExternalProductPolicyDto> externalProductPolicyDtos){
        return externalProductPolicyDtos.stream().map(e ->
            new ExternalSiteProductPolicy(
                    e.getSiteId(),
                    e.getPolicyId(),
                    e.getCountryCode(),
                    e.isTranslated()
            )
        ).toList();
    }

}
