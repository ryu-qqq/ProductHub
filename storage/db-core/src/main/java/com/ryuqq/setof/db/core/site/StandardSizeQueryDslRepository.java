package com.ryuqq.setof.storage.db.core.site;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.site.dto.QStandardSizeDto;
import com.ryuqq.setof.storage.db.core.site.dto.StandardSizeDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.site.QSizeRegionEntity.sizeRegionEntity;
import static com.ryuqq.setof.storage.db.core.site.QStandardSizeEntity.standardSizeEntity;

@Repository
public class StandardSizeQueryDslRepository implements StandardSizeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public StandardSizeQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<StandardSizeDto> fetchByCategoryIds(List<Long> categoryIds) {
        return queryFactory.select(
                new QStandardSizeDto(
                        standardSizeEntity.categoryId,
                        standardSizeEntity.regionId,
                        sizeRegionEntity.name,
                        standardSizeEntity.sizeValue
                )
                )
                .from(standardSizeEntity)
                .innerJoin(sizeRegionEntity)
                    .on(sizeRegionEntity.id.eq(standardSizeEntity.regionId))
                .where(
                        categoryIdIn(categoryIds)
                )
                .fetch();


    }


    private BooleanExpression categoryIdIn(List<Long> categoryIds){
        return standardSizeEntity.categoryId.in(categoryIds);
    }

}
