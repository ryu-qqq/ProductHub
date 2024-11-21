package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.storage.db.core.site.ExternalSiteQueryRepository;
import com.ryuqq.setof.storage.db.core.site.dto.SellerSiteRelationDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalSiteRelationFinder {

    private final ExternalSiteQueryRepository externalSiteQueryRepository;

    public ExternalSiteRelationFinder(ExternalSiteQueryRepository externalSiteQueryRepository) {
        this.externalSiteQueryRepository = externalSiteQueryRepository;
    }


    public List<SellerSiteRelationDto> findSellerSiteRelation(Long siteId, List<Long> sellerIds){
        return externalSiteQueryRepository.fetchSellerSiteRelation(siteId, sellerIds);
    }

}
