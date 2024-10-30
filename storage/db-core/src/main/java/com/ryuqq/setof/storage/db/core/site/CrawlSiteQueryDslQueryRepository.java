package com.ryuqq.setof.storage.db.core.site;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.storage.db.core.site.dto.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.ryuqq.setof.storage.db.core.site.QCrawlEndpointEntity.crawlEndpointEntity;
import static com.ryuqq.setof.storage.db.core.site.QCrawlSettingEntity.crawlSettingEntity;
import static com.ryuqq.setof.storage.db.core.site.QSiteAuthSettingEntity.siteAuthSettingEntity;
import static com.ryuqq.setof.storage.db.core.site.QSiteEntity.siteEntity;

@Repository
public class CrawlSiteQueryDslQueryRepository {

    private final JPAQueryFactory queryFactory;

    public CrawlSiteQueryDslQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Optional<CrawlSiteProfileDto> fetchSiteProfile(long siteId, SiteType siteType) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(siteEntity)
                        .innerJoin(crawlSettingEntity)
                            .on(crawlSettingEntity.siteId.eq(siteId))
                        .innerJoin(crawlEndpointEntity)
                            .on(crawlEndpointEntity.siteId.eq(siteId))
                        .innerJoin(siteAuthSettingEntity)
                            .on(siteAuthSettingEntity.siteId.eq(siteId))
                        .where(
                                siteIdEq(siteId),
                                siteTypeEq(siteType)
                        ).transform(
                                GroupBy.groupBy(siteEntity.id).as(
                                        new QCrawlSiteProfileDto(
                                                ConstantImpl.create(siteType),
                                                crawlSettingEntity.crawlFrequency,
                                                crawlSettingEntity.crawlType,
                                                new QCrawlAuthSettingDto(
                                                        siteAuthSettingEntity.authType,
                                                        siteAuthSettingEntity.authEndpoint,
                                                        siteAuthSettingEntity.authHeaders,
                                                        siteAuthSettingEntity.authPayload
                                                ),
                                                GroupBy.list(
                                                        new QCrawlEndPointDto(
                                                                crawlEndpointEntity.endPointUrl,
                                                                crawlEndpointEntity.crawlFrequency
                                                        )
                                                )
                                        )
                                )
                        ).get(siteId)
        );
    }

    private BooleanExpression siteIdEq(long siteId){
        return siteEntity.id.eq(siteId);
    }

    private BooleanExpression siteTypeEq(SiteType siteType){
        return siteEntity.siteType.eq(siteType);
    }



}
