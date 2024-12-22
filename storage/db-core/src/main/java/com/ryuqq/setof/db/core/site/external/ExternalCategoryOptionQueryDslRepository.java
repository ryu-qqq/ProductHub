package com.ryuqq.setof.storage.db.core.site.external;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalCategoryOptionDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.QExternalCategoryOptionDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.category.QMappingCategoryEntity.mappingCategoryEntity;
import static com.ryuqq.setof.storage.db.core.site.external.QExternalCategoryOptionEntity.externalCategoryOptionEntity;

@Repository
public class ExternalCategoryOptionQueryDslRepository implements ExternalCategoryOptionQueryRepository{

    private final JPAQueryFactory queryFactory;

    public ExternalCategoryOptionQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ExternalCategoryOptionDto> fetchBySiteIdAndCategoryIds(long siteId, List<Long> categoryIds) {
        return queryFactory.select(
                    new QExternalCategoryOptionDto(
                            externalCategoryOptionEntity.siteId,
                            externalCategoryOptionEntity.externalCategoryId,
                            externalCategoryOptionEntity.optionGroupId,
                            externalCategoryOptionEntity.optionId,
                            externalCategoryOptionEntity.optionValue
                    )
                )
                .from(externalCategoryOptionEntity)
                .innerJoin(mappingCategoryEntity)
                .on(mappingCategoryEntity.siteCategoryId.eq(externalCategoryOptionEntity.externalCategoryId)
                        .or(mappingCategoryEntity.siteCategoryExtraId.eq(externalCategoryOptionEntity.externalCategoryId))
                )
                .where(
                        siteIdEq(siteId),
                        categoryIdIn(categoryIds)
                ).fetch();
    }

    private BooleanExpression categoryIdIn(List<Long> categoryIds){
        return mappingCategoryEntity.internalCategoryId.in(categoryIds);
    }

    private BooleanExpression siteIdEq(long siteId){
        return externalCategoryOptionEntity.siteId.eq(siteId);
    }

}
