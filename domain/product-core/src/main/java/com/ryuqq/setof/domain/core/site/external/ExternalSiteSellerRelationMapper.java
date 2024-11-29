package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductPolicyDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalSiteSellerRelationDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalSiteSellerRelationMapper {

    public ExternalSiteSellerRelation toDomain(ExternalSiteSellerRelationDto externalSiteSellerRelation){
        return new ExternalSiteSellerRelation(
                externalSiteSellerRelation.getSellerId(),
                toExternalSiteProductPolicies(externalSiteSellerRelation.getExternalProductPolicyDtos())
        );

    }

    private List<ExternalProductPolicy> toExternalSiteProductPolicies(List<ExternalProductPolicyDto> externalProductPolicyDtos){
        return externalProductPolicyDtos.stream().map(e ->
                new ExternalProductPolicy(
                        e.getSiteId(),
                        e.getPolicyId(),
                        e.getCountryCode(),
                        e.isTranslated()
                )
        ).toList();
    }

}
