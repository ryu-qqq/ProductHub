package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.db.core.site.external.ExternalSiteQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalSiteSellerRelationFinder implements ExternalSiteSellerRelationQueryService{

    private final ExternalSiteSellerRelationMapper externalSiteSellerRelationMapper;
    private final ExternalSiteQueryRepository externalSiteQueryRepository;

    public ExternalSiteSellerRelationFinder(ExternalSiteSellerRelationMapper externalSiteSellerRelationMapper, ExternalSiteQueryRepository externalSiteQueryRepository) {
        this.externalSiteSellerRelationMapper = externalSiteSellerRelationMapper;
        this.externalSiteQueryRepository = externalSiteQueryRepository;
    }

    @Override
    public boolean existBySellerIdAndSitId(long sellerId, long siteId) {
        return externalSiteQueryRepository.existBySellerIdAndSitId(sellerId, siteId);
    }


    @Override
    public List<ExternalSiteSellerRelation> findExternalSiteSellerRelation(List<Long> sellerIds){
        return externalSiteQueryRepository.fetchBySellerId(sellerIds).stream()
                .map(externalSiteSellerRelationMapper::toDomain)
                .toList();
    }

}
