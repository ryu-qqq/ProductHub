package com.ryuqq.setof.storage.db.core.site.external;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalCategoryOptionDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.QExternalCategoryOptionDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.site.QSiteEntity.siteEntity;
import static com.ryuqq.setof.storage.db.core.site.external.QExternalCategoryOptionEntity.externalCategoryOptionEntity;

@Repository
public class ExternalCategoryOptionQueryDslRepository implements ExternalCategoryOptionQueryRepository{

    private final JPAQueryFactory queryFactory;

    public ExternalCategoryOptionQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ExternalCategoryOptionDto> fetchBySiteNameAndCategoryId(SiteName siteName, long categoryId) {
        return queryFactory.select(
                    new QExternalCategoryOptionDto(
                            externalCategoryOptionEntity.siteId,
                            externalCategoryOptionEntity.externalCategoryId,
                            externalCategoryOptionEntity.optionId,
                            externalCategoryOptionEntity.optionValue
                    )
                )
                .from(externalCategoryOptionEntity)
                .innerJoin(siteEntity)
                    .on(siteEntity.name.eq(siteName.name()))
                    .on(siteEntity.id.eq(externalCategoryOptionEntity.siteId))
                .where(
                        externalCategoryIdEq(categoryId)
                ).fetch();
    }

    private BooleanExpression externalCategoryIdEq(long externalCategoryId){
        return externalCategoryOptionEntity.externalCategoryId.eq(externalCategoryId);
    }

}
