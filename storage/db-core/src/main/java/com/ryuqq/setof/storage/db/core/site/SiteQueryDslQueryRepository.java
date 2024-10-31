package com.ryuqq.setof.storage.db.core.site;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.site.dto.QSiteContextDto;
import com.ryuqq.setof.storage.db.core.site.dto.SiteContextDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.ryuqq.setof.storage.db.core.site.QSiteEntity.siteEntity;

@Repository
public class SiteQueryDslQueryRepository implements SiteQueryRepository{

    private final JPAQueryFactory queryFactory;

    public SiteQueryDslQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<SiteContextDto> fetchSiteContext(long siteId) {
        return Optional.ofNullable(
                queryFactory.select(
                        new QSiteContextDto(
                                ConstantImpl.create(siteId),
                                siteEntity.name,
                                siteEntity.baseUrl,
                                siteEntity.countryCode,
                                siteEntity.siteType
                        ))
                .from(siteEntity)
                .where(
                        siteIdEq(siteId)
                ).fetchOne());
    }


    private BooleanExpression siteIdEq(long siteId){
        return siteEntity.id.eq(siteId);
    }

}
