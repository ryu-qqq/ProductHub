package com.ryuqq.setof.storage.db.core.site;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.site.dto.QSellerSiteRelationDto;
import com.ryuqq.setof.storage.db.core.site.dto.QSitePolicyDto;
import com.ryuqq.setof.storage.db.core.site.dto.SellerSiteRelationDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.site.QExternalPolicyEntity.externalPolicyEntity;
import static com.ryuqq.setof.storage.db.core.site.QExternalSiteSellerEntity.externalSiteSellerEntity;


@Repository
public class ExternalSiteQueryDslRepository implements ExternalSiteQueryRepository{

    private final JPAQueryFactory queryFactory;

    public ExternalSiteQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<SellerSiteRelationDto> fetchSellerSiteRelation(Long siteId, List<Long> sellerIds) {
        return queryFactory.from(externalSiteSellerEntity)
                .leftJoin(externalPolicyEntity)
                    .on(externalPolicyEntity.siteId.eq(externalSiteSellerEntity.siteId))
                .where(
                        siteIdEq(siteId),
                        sellerIdIn(sellerIds),
                        activeStatusEq()
                ).transform(GroupBy.groupBy(externalSiteSellerEntity.sellerId).list(
                            new QSellerSiteRelationDto(
                                    externalSiteSellerEntity.sellerId,
                                    GroupBy.list(
                                            new QSitePolicyDto(
                                                    externalSiteSellerEntity.siteId,
                                                    externalPolicyEntity.id
                                            )
                                    )
                            )
                ));
    }

    private BooleanExpression siteIdEq(Long siteId){
        if(siteId == null) return null;
        return externalSiteSellerEntity.siteId.eq(siteId);
    }

    private BooleanExpression sellerIdIn(List<Long> sellerIds){
        return externalSiteSellerEntity.sellerId.in(sellerIds);
    }

    private BooleanExpression activeStatusEq(){
        return externalSiteSellerEntity.activeStatus.eq(true);
    }


}
