package com.ryuqq.setof.storage.db.core.site;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.storage.db.core.site.dto.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ryuqq.setof.storage.db.core.site.QCrawlEndpointEntity.crawlEndpointEntity;
import static com.ryuqq.setof.storage.db.core.site.QCrawlMappingEntity.crawlMappingEntity;
import static com.ryuqq.setof.storage.db.core.site.QCrawlSettingEntity.crawlSettingEntity;
import static com.ryuqq.setof.storage.db.core.site.QCrawlTaskEntity.crawlTaskEntity;
import static com.ryuqq.setof.storage.db.core.site.QSiteAuthSettingEntity.siteAuthSettingEntity;
import static com.ryuqq.setof.storage.db.core.site.QSiteEntity.siteEntity;

@Repository
public class CrawlSiteQueryDslQueryRepository {

    private final JPAQueryFactory queryFactory;

    public CrawlSiteQueryDslQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<CrawlSiteProfileDto> fetchSiteProfile(long siteId, SiteType siteType) {

        return
                queryFactory
                        .selectFrom(siteEntity)
                        .innerJoin(crawlSettingEntity)
                            .on(crawlSettingEntity.siteId.eq(siteId))
                        .innerJoin(siteAuthSettingEntity)
                            .on(siteAuthSettingEntity.siteId.eq(siteId))
                        .innerJoin(crawlMappingEntity)
                            .on(crawlMappingEntity.crawlSettingId.eq(crawlSettingEntity.id))
                            .on(crawlMappingEntity.authSettingId.eq(siteAuthSettingEntity.id))
                        .where(
                                siteIdEq(siteId),
                                siteTypeEq(siteType)
                        ).transform(
                                GroupBy.groupBy(siteEntity.id).list(
                                        new QCrawlSiteProfileDto(
                                                crawlMappingEntity.id,
                                                ConstantImpl.create(siteType),
                                                crawlSettingEntity.crawlFrequency,
                                                crawlSettingEntity.crawlType,
                                                new QCrawlAuthSettingDto(
                                                        siteAuthSettingEntity.authType,
                                                        siteAuthSettingEntity.authEndpoint,
                                                        siteAuthSettingEntity.authHeaders,
                                                        siteAuthSettingEntity.authPayload
                                                )
                                        )
                                )
                        );

    }

    public List<CrawlEndPointDto> fetchCrawlEndPoints(long siteId){
        return queryFactory
                .selectFrom(crawlEndpointEntity)
                .innerJoin(crawlTaskEntity)
                    .on(crawlTaskEntity.endpointId.eq(crawlEndpointEntity.id))
                    .on(crawlTaskEntity.deleteYn.eq(false))
                .where(
                        crawlEndpointEntity.siteId.eq(siteId),
                        crawlEndpointEntity.deleteYn.eq(false)
                ).transform(
                        GroupBy.groupBy(crawlEndpointEntity.id).list(
                                new QCrawlEndPointDto(
                                        crawlEndpointEntity.crawlMappingId,
                                        crawlEndpointEntity.endPointUrl,
                                        crawlEndpointEntity.parameters.coalesce(""),
                                        GroupBy.list(
                                                new QCrawlTaskDto(
                                                        crawlTaskEntity.endpointId,
                                                        crawlTaskEntity.stepOrder,
                                                        crawlTaskEntity.taskType,
                                                        crawlTaskEntity.actionTarget.coalesce(""),
                                                        crawlTaskEntity.actionType,
                                                        crawlTaskEntity.params.coalesce(""),
                                                        crawlTaskEntity.responseMapping.coalesce("")
                                                )
                                        )
                                )
                        )
                );
    }


    private BooleanExpression siteIdEq(long siteId){
        return siteEntity.id.eq(siteId);
    }

    private BooleanExpression siteTypeEq(SiteType siteType){
        return siteEntity.siteType.eq(siteType);
    }



}
