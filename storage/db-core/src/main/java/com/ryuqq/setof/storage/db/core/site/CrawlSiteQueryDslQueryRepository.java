package com.ryuqq.setof.storage.db.core.site;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.storage.db.core.site.dto.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    public List<CrawlSiteProfileDto> fetchSiteProfile(long siteId) {

        return
                queryFactory
                        .selectFrom(crawlMappingEntity)
                        .innerJoin(crawlSettingEntity)
                            .on(crawlSettingEntity.siteId.eq(siteId))
                            .on(crawlMappingEntity.crawlSettingId.eq(crawlSettingEntity.id))
                        .innerJoin(siteAuthSettingEntity)
                            .on(siteAuthSettingEntity.siteId.eq(siteId))
                            .on(crawlMappingEntity.authSettingId.eq(siteAuthSettingEntity.id))
                        .where(
                                siteIdEq(siteId)
                        ).transform(
                                GroupBy.groupBy(crawlMappingEntity.id).list(
                                        new QCrawlSiteProfileDto(
                                                crawlMappingEntity.id,
                                                crawlSettingEntity.id,
                                                crawlSettingEntity.crawlFrequency,
                                                crawlSettingEntity.crawlType,
                                                new QCrawlAuthSettingDto(
                                                        siteAuthSettingEntity.id,
                                                        siteAuthSettingEntity.authType,
                                                        siteAuthSettingEntity.authEndpoint,
                                                        siteAuthSettingEntity.authHeaders,
                                                        siteAuthSettingEntity.authPayload
                                                )
                                        )
                                )
                        );
    }

    public Optional<CrawlSiteProfileDto> fetchSiteProfile(long siteId, long mappingId) {
        return
                Optional.ofNullable(queryFactory
                        .select(
                                new QCrawlSiteProfileDto(
                                        crawlMappingEntity.id,
                                        crawlSettingEntity.id,
                                        crawlSettingEntity.crawlFrequency,
                                        crawlSettingEntity.crawlType,
                                        new QCrawlAuthSettingDto(
                                                siteAuthSettingEntity.id,
                                                siteAuthSettingEntity.authType,
                                                siteAuthSettingEntity.authEndpoint,
                                                siteAuthSettingEntity.authHeaders,
                                                siteAuthSettingEntity.authPayload
                                        )
                                )
                        )
                        .from(crawlMappingEntity)
                        .innerJoin(crawlSettingEntity)
                        .on(crawlSettingEntity.siteId.eq(siteId))
                        .on(crawlMappingEntity.crawlSettingId.eq(crawlSettingEntity.id))
                        .innerJoin(siteAuthSettingEntity)
                        .on(siteAuthSettingEntity.siteId.eq(siteId))
                        .on(crawlMappingEntity.authSettingId.eq(siteAuthSettingEntity.id))
                        .where(
                                siteIdEq(siteId), mappingIdEq(mappingId)
                        ).fetchOne());
    }

    public List<CrawlEndPointDto> fetchCrawlEndPoints(long siteId, Long mappingId){
        return queryFactory
                .selectFrom(crawlEndpointEntity)
                .innerJoin(crawlTaskEntity)
                    .on(crawlTaskEntity.endpointId.eq(crawlEndpointEntity.id))
                    .on(crawlTaskEntity.deleteYn.eq(false))
                .where(
                        crawlEndpointEntity.siteId.eq(siteId),
                        crawlEndpointEntity.deleteYn.eq(false),
                        crawlMappingIdEq(mappingId)
                ).transform(
                        GroupBy.groupBy(crawlEndpointEntity.id).list(
                                new QCrawlEndPointDto(
                                        crawlTaskEntity.endpointId,
                                        crawlEndpointEntity.crawlMappingId,
                                        crawlEndpointEntity.endPointUrl,
                                        crawlEndpointEntity.parameters.coalesce(""),
                                        GroupBy.list(
                                                new QCrawlTaskDto(
                                                        crawlTaskEntity.endpointId,
                                                        crawlTaskEntity.stepOrder,
                                                        crawlTaskEntity.processType,
                                                        crawlTaskEntity.actionTarget.coalesce(""),
                                                        crawlTaskEntity.actionType,
                                                        crawlTaskEntity.params.coalesce(""),
                                                        crawlTaskEntity.endPointUrl.coalesce(""),
                                                        crawlTaskEntity.responseMapping.coalesce("")
                                                )
                                        )
                                )
                        )
                );
    }


    private BooleanExpression siteIdEq(long siteId){
        return crawlMappingEntity.siteId.eq(siteId);
    }

    private BooleanExpression mappingIdEq(Long mappingId){
        if(mappingId == null) return null;
        return crawlMappingEntity.id.eq(mappingId);
    }

    private BooleanExpression crawlMappingIdEq(Long crawlMappingId){
        if(crawlMappingId == null) return null;
        return crawlEndpointEntity.crawlMappingId.eq(crawlMappingId);
    }

}
