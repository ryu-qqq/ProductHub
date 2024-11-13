package com.ryuqq.setof.storage.db.core.site;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlProductDto;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlProductStorageDto;
import com.ryuqq.setof.storage.db.core.site.dto.QCrawlProductDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.site.QCrawlProductEntity.crawlProductEntity;
import static com.ryuqq.setof.storage.db.core.site.QSiteEntity.siteEntity;

@Repository
public class CrawlProductQueryDslRepository implements CrawlProductQueryRepository {

    public List<CrawlProductDto> fetchCrawlProducts(CrawlProductStorageDto crawlProductStorageDto) {

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
                        isProductGroupNull(crawlProductStorageDto.isProductGroupIdNull()),
                        crawlProductIdGt(crawlProductStorageDto.cursorId())
                )
                .limit(crawlProductStorageDto.pageSize())
                .orderBy(crawlProductEntity.id.asc())
                .fetch();
    }

    @Override
    public long fetchCrawlProductCount(CrawlProductStorageDto crawlProductStorageDto) {
        Long count = queryFactory.select(
                        crawlProductEntity.count()
                ).from(crawlProductEntity)
                .innerJoin(siteEntity)
                .on(siteEntity.id.eq(crawlProductEntity.siteId))
                .where(
                        isProductGroupNull(crawlProductStorageDto.isProductGroupIdNull())
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
