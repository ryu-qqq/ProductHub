package com.ryuqq.setof.storage.db.core.site.external;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalPolicyDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.QExternalPolicyDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.site.external.QExternalPolicyEntity.externalPolicyEntity;


@Repository
public class ExternalPolicyQueryDslRepository implements ExternalPolicyQueryRepository{

    private final JPAQueryFactory queryFactory;

    public ExternalPolicyQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ExternalPolicyDto> fetchBySiteId(List<Long> siteIds) {
        return queryFactory.select(
                        new QExternalPolicyDto(
                                externalPolicyEntity.siteId,
                                externalPolicyEntity.id,
                                externalPolicyEntity.name.coalesce(""),
                                externalPolicyEntity.description.coalesce(""),
                                externalPolicyEntity.activeYn
                        )
                )
                .from(externalPolicyEntity)
                .where(externalPolicyEntity.siteId.in(siteIds))
                .fetch();
    }

}
