package com.ryuqq.setof.storage.db.core.site.external;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductPolicyDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.QExternalProductPolicyDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.site.external.QExternalPolicyEntity.externalPolicyEntity;
import static com.ryuqq.setof.storage.db.core.site.external.QExternalProductPolicyEntity.externalProductPolicyEntity;

@Repository
public class ExternalProductPolicyQueryDslRepository implements ExternalProductPolicyQueryRepository{

    private final JPAQueryFactory queryFactory;


    public ExternalProductPolicyQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ExternalProductPolicyDto> fetchBySiteIds(List<Long> siteIds) {
        return queryFactory.select(
                        new QExternalProductPolicyDto(
                                externalPolicyEntity.siteId,
                                externalPolicyEntity.id,
                                externalProductPolicyEntity.countryCode,
                                externalProductPolicyEntity.translated
                        )
                )
                .from(externalPolicyEntity)
                .innerJoin(externalProductPolicyEntity)
                .on(externalProductPolicyEntity.policyId.eq(externalPolicyEntity.id))
                .where(externalPolicyEntity.siteId.in(siteIds))
                .fetch();
    }

}
