package com.ryuqq.setof.storage.db.core.site.external;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.site.QSiteEntity;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalPolicyDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.QExternalPolicyDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.site.QSiteEntity.siteEntity;
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
                                siteEntity.name,
                                externalPolicyEntity.id,
                                externalPolicyEntity.name.coalesce(""),
                                externalPolicyEntity.description.coalesce(""),
                                externalPolicyEntity.activeYn
                        )
                )
                .from(externalPolicyEntity)
                .innerJoin(siteEntity)
                    .on(siteEntity.id.eq(externalPolicyEntity.siteId))
                .where(externalPolicyEntity.siteId.in(siteIds))
                .fetch();
    }

}
