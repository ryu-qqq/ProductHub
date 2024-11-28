package com.ryuqq.setof.storage.db.core.brand;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.brand.dto.MappingBrandDto;
import com.ryuqq.setof.storage.db.core.brand.dto.QMappingBrandDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.brand.QBrandEntity.brandEntity;
import static com.ryuqq.setof.storage.db.core.brand.QMappingBrandEntity.mappingBrandEntity;

@Repository
public class MappingBrandQueryDslRepository implements MappingBrandQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MappingBrandQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<MappingBrandDto> fetchByBrandIdAndSiteId(long siteId, List<Long> brandIds){
        return queryFactory.select(
                    new QMappingBrandDto(
                            mappingBrandEntity.siteBrandId,
                            mappingBrandEntity.internalBrandId,
                            brandEntity.brandName
                    )
                )
                .from(mappingBrandEntity)
                .innerJoin(brandEntity)
                    .on(brandEntity.id.eq(mappingBrandEntity.internalBrandId))
                .where(brandIdIn(brandIds), siteIdEq(siteId))
                .fetch();
    }



    private BooleanExpression brandIdIn(List<Long> brandIds){
        return (brandIds != null && !brandIds.isEmpty()) ? mappingBrandEntity.internalBrandId.in(brandIds) : null;
    }

    private BooleanExpression siteIdEq(long siteId){
        return mappingBrandEntity.siteId.eq(siteId);
    }



}
