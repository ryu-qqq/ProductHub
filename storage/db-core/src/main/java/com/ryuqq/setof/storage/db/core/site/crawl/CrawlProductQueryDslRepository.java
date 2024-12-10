package com.ryuqq.setof.storage.db.core.site.crawl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.site.crawl.dto.CrawlProductDto;
import com.ryuqq.setof.storage.db.core.site.crawl.dto.CrawlProductStorageFilterDto;
import com.ryuqq.setof.storage.db.core.site.crawl.dto.QCrawlProductDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.site.QSiteEntity.siteEntity;
import static com.ryuqq.setof.storage.db.core.site.crawl.QCrawlProductEntity.crawlProductEntity;

@Repository
public class CrawlProductQueryDslRepository implements CrawlProductQueryRepository {

    public List<CrawlProductDto> fetchByFilter(CrawlProductStorageFilterDto crawlProductStorageFilterDto) {

        return queryFactory.select(
                    new QCrawlProductDto(
                            crawlProductEntity.id,
                            crawlProductEntity.siteId,
                            siteEntity.name,
                            crawlProductEntity.siteProductId.coalesce(""),
                            crawlProductEntity.productName,
                            crawlProductEntity.productGroupId
                    )
                ).from(crawlProductEntity)
                .innerJoin(siteEntity)
                .on(siteEntity.id.eq(crawlProductEntity.siteId))
                .where(
                        isProductGroupNull(crawlProductStorageFilterDto.isProductGroupIdNull()),
                        crawlProductIdGt(crawlProductStorageFilterDto.cursorId())
                )
                .limit(crawlProductStorageFilterDto.pageSize())
                .orderBy(crawlProductEntity.id.asc())
                .fetch();
    }

    @Override
    public long countByFilter(CrawlProductStorageFilterDto crawlProductStorageFilterDto) {
        Long count = queryFactory.select(
                        crawlProductEntity.count()
                ).from(crawlProductEntity)
                .innerJoin(siteEntity)
                .on(siteEntity.id.eq(crawlProductEntity.siteId))
                .where(
                        isProductGroupNull(crawlProductStorageFilterDto.isProductGroupIdNull())
                )
                .fetchOne();

        return count !=null ? count : 0;
    }

    private final JPAQueryFactory queryFactory;

    public CrawlProductQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


    private BooleanExpression isProductGroupNull(boolean isProductGroupIdNull){
        if(isProductGroupIdNull) return crawlProductEntity.productGroupId.isNull();
        return crawlProductEntity.productGroupId.isNotNull();
    }

    private BooleanExpression crawlProductIdGt(Long cursorId){
        if(cursorId != null) return crawlProductEntity.id.gt(cursorId);
        return null;
    }

}
