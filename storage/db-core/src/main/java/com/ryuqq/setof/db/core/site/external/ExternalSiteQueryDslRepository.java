package com.ryuqq.setof.storage.db.core.site.external;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalSiteSellerRelationDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.QExternalProductPolicyDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.QExternalSiteSellerRelationDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.site.external.QExternalPolicyEntity.externalPolicyEntity;
import static com.ryuqq.setof.storage.db.core.site.external.QExternalProductPolicyEntity.externalProductPolicyEntity;
import static com.ryuqq.setof.storage.db.core.site.external.QExternalSiteSellerEntity.externalSiteSellerEntity;


@Repository
public class ExternalSiteQueryDslRepository implements ExternalSiteQueryRepository{

    private final JPAQueryFactory queryFactory;

    public ExternalSiteQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public boolean existBySellerIdAndSitId(long sellerId, long siteId) {
        Long l = queryFactory
                .select(externalSiteSellerEntity.id)
                .from(externalSiteSellerEntity)
                .where(
                        activeStatusEq(),
                        sellerIdEq(sellerId),
                        siteIdEq(siteId)
                )
                .fetchOne();

        return l != null;
    }


    @Override
    public List<ExternalSiteSellerRelationDto> fetchBySellerId(List<Long> sellerIds) {
        return queryFactory
                .from(externalSiteSellerEntity)
                .leftJoin(externalPolicyEntity)
                    .on(externalPolicyEntity.siteId.eq(externalSiteSellerEntity.siteId))
                .leftJoin(externalProductPolicyEntity)
                    .on(externalProductPolicyEntity.policyId.eq(externalPolicyEntity.id))
                .where(
                        sellerIdIn(sellerIds),
                        activeStatusEq()
                )
                .transform(GroupBy.groupBy(externalSiteSellerEntity.sellerId).list(
                            new QExternalSiteSellerRelationDto(
                                    externalSiteSellerEntity.sellerId,
                                    GroupBy.list(
                                            new QExternalProductPolicyDto(
                                                    externalSiteSellerEntity.siteId,
                                                    externalPolicyEntity.id,
                                                    externalProductPolicyEntity.countryCode,
                                                    externalProductPolicyEntity.translated
                                            )
                                    )
                            )
                ));
    }

    private BooleanExpression sellerIdEq(long sellerId){
        return externalSiteSellerEntity.sellerId.eq(sellerId);
    }

    private BooleanExpression siteIdEq(long siteId){
        return externalSiteSellerEntity.siteId.eq(siteId);
    }

    private BooleanExpression sellerIdIn(List<Long> sellerIds){
        return externalSiteSellerEntity.sellerId.in(sellerIds);
    }

    private BooleanExpression activeStatusEq(){
        return externalSiteSellerEntity.activeStatus.eq(true);
    }


}
