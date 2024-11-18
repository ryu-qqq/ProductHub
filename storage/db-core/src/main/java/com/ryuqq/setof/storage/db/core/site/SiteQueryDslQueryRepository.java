package com.ryuqq.setof.storage.db.core.site;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.enums.core.SiteType;
import com.ryuqq.setof.storage.db.core.site.dto.QSiteContextDto;
import com.ryuqq.setof.storage.db.core.site.dto.SiteContextDto;
import com.ryuqq.setof.storage.db.core.site.dto.SiteFilterStorageDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ryuqq.setof.storage.db.core.site.QSiteEntity.siteEntity;

@Repository
public class SiteQueryDslQueryRepository implements SiteQueryRepository{

    private final JPAQueryFactory queryFactory;

    public SiteQueryDslQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public boolean fetchSiteExists(long siteId) {
        Long findId = queryFactory.select(
                        siteEntity.id)
                .from(siteEntity)
                .where(
                        siteIdEq(siteId)
                ).fetchOne();

        return findId != null;
    }

    @Override
    public boolean fetchSiteExists(String name, String baseUrl) {
        Long siteId = queryFactory.select(
                        siteEntity.id)
                .from(siteEntity)
                .where(
                        siteNameEq(name),
                        baseUrlEq(baseUrl)

                ).fetchOne();

        return siteId != null;
    }

    @Override
    public List<SiteContextDto> fetchSites(SiteFilterStorageDto siteFilterStorageDto) {
        return queryFactory.select(
                        new QSiteContextDto(
                                siteEntity.id,
                                siteEntity.name,
                                siteEntity.baseUrl,
                                siteEntity.countryCode,
                                siteEntity.siteType
                        ))
                .from(siteEntity)
                .limit(siteFilterStorageDto.pageSize())
                .orderBy(siteEntity.id.desc())
                .where(
                        siteTypeEq(siteFilterStorageDto.siteType()),
                        isSiteIdLt(siteFilterStorageDto.cursorId())
                ).fetch();
    }

    @Override
    public long fetchSiteCount(SiteFilterStorageDto siteFilterStorageDto) {
        Long count = queryFactory.select(
                        siteEntity.count()
                )
                .from(siteEntity)
                .where(
                        siteTypeEq(siteFilterStorageDto.siteType()),
                        isSiteIdLt(siteFilterStorageDto.cursorId())

                ).fetchOne();

        return count == null ? 0 : count;
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

    private BooleanExpression siteTypeEq(SiteType siteType){
        if(siteType == null) return null;
        return siteEntity.siteType.eq(siteType);
    }

    private BooleanExpression isSiteIdLt(Long siteId){
        if(siteId !=null) return siteEntity.id.lt(siteId);
        else return null;
    }

    private BooleanExpression siteNameEq(String name){
        return siteEntity.name.eq(name);
    }

    private BooleanExpression baseUrlEq(String baseUrl){
        return siteEntity.baseUrl.eq(baseUrl);
    }



}
