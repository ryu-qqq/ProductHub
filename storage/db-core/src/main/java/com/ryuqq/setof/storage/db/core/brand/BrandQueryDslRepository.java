package com.ryuqq.setof.storage.db.core.brand;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.brand.dto.BrandDto;
import com.ryuqq.setof.storage.db.core.brand.dto.BrandStorageFilterDto;
import com.ryuqq.setof.storage.db.core.brand.dto.QBrandDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ryuqq.setof.storage.db.core.brand.QBrandEntity.brandEntity;

@Repository
public class BrandQueryDslRepository implements BrandQueryRepository {

    private final JPAQueryFactory queryFactory;

    public BrandQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public boolean existById(long brandId) {
        Long brandOpt = queryFactory
                .select(brandEntity.id)
                .from(brandEntity)
                .where(brandIdEq(brandId))
                .fetchFirst();

        return brandOpt != null;
    }

    @Override
    public Optional<BrandDto> fetchById(long brandId) {
        return Optional.ofNullable(queryFactory
                        .select(
                                new QBrandDto(
                                        brandEntity.id,
                                        brandEntity.brandName,
                                        brandEntity.brandNameKr,
                                        brandEntity.brandIconImageUrl.coalesce(""),
                                        brandEntity.displayYn
                                )
                        )
                        .from(brandEntity)
                        .where(brandIdEq(brandId))
                        .fetchOne());
    }

    @Override
    public long countByFilter(BrandStorageFilterDto brandFilter) {
        Long count = queryFactory.select(
                        brandEntity.count()
                )
                .from(brandEntity)
                .where(
                        brandIdIn(brandFilter.brandIds())
                ).fetchOne();

        return count != null ? count : 0L;
    }

    @Override
    public List<BrandDto> fetchByFilter(BrandStorageFilterDto brandFilter) {
        return queryFactory
                .select(
                        new QBrandDto(
                                brandEntity.id,
                                brandEntity.brandName,
                                brandEntity.brandNameKr,
                                brandEntity.brandIconImageUrl.coalesce(""),
                                brandEntity.displayYn
                        )
                )
                .from(brandEntity)
                .orderBy(brandEntity.id.desc())
                .limit(brandFilter.pageSize())
                .where(
                        brandIdIn(brandFilter.brandIds()),
                        isBrandIdLt(brandFilter.cursorId())
                )
                .fetch();

    }


    private BooleanExpression brandIdEq(long brandId){
        return brandEntity.id.eq(brandId);
    }

    private BooleanExpression brandIdIn(List<Long> brandIds){
        return (brandIds != null && !brandIds.isEmpty()) ? brandEntity.id.in(brandIds) : null;
    }

    private BooleanExpression isBrandIdLt(Long brandId){
        if(brandId !=null) return brandEntity.id.lt(brandId);
        else return null;
    }

}
