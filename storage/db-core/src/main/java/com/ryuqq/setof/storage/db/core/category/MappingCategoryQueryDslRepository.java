package com.ryuqq.setof.storage.db.core.category;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.category.dto.MappingCategoryDto;
import com.ryuqq.setof.storage.db.core.category.dto.QMappingCategoryDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.category.QCategoryEntity.categoryEntity;
import static com.ryuqq.setof.storage.db.core.category.QMappingCategoryEntity.mappingCategoryEntity;

@Repository
public class MappingCategoryQueryDslRepository implements MappingCategoryQueryRepository{

    private final JPAQueryFactory queryFactory;

    public MappingCategoryQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<MappingCategoryDto> fetchByCategoryIdAndSiteId(long siteId, List<Long> categoryIds){
        return queryFactory.select(
                        new QMappingCategoryDto(
                                mappingCategoryEntity.siteCategoryId,
                                categoryEntity.id,
                                categoryEntity.categoryName,
                                categoryEntity.targetGroup,
                                categoryEntity.categoryType
                        )
                )
                .from(mappingCategoryEntity)
                .innerJoin(categoryEntity)
                .on(categoryEntity.id.eq(mappingCategoryEntity.internalCategoryId))
                .where(categoryIdIn(categoryIds), siteIdEq(siteId))
                .fetch();
    }



    private BooleanExpression categoryIdIn(List<Long> categoryIds){
        return (categoryIds != null && !categoryIds.isEmpty()) ? mappingCategoryEntity.internalCategoryId.in(categoryIds) : null;
    }

    private BooleanExpression siteIdEq(long siteId){
        return mappingCategoryEntity.siteId.eq(siteId);
    }


}
