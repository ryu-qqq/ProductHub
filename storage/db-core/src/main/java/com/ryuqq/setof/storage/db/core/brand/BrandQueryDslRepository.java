package com.ryuqq.setof.storage.db.core.brand;

import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.domain.core.brand.BrandFilter;
import com.ryuqq.setof.domain.core.brand.BrandQueryRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.brand.dao.BrandDao;
import com.ryuqq.setof.storage.db.core.brand.dao.QBrandDao;
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
    public boolean fetchBrandExists(long brandId) {
        Long brandOpt = queryFactory
                .select(brandEntity.id)
                .from(brandEntity)
                .where(brandIdEq(brandId))
                .fetchFirst();

        return brandOpt != null;
    }

    @Override
    public Optional<Brand> fetchBrand(long brandId) {
        BrandDao brandDao = queryFactory
                        .select(
                                new QBrandDao(
                                        brandEntity.id,
                                        brandEntity.brandName,
                                        brandEntity.brandIconImageUrl.coalesce(""),
                                        brandEntity.displayYn
                                )
                        )
                        .from(brandEntity)
                        .where(brandIdEq(brandId))
                        .fetchOne();

        if(brandDao == null) {
            return Optional.empty();
        }else{
            return Optional.ofNullable(brandDao.toBrand());
        }

    }

    @Override
    public long fetchBrandCount(BrandFilter brandFilter) {
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
    public List<Brand> fetchBrands(BrandFilter brandFilter) {
        List<BrandDao> brands = queryFactory
                .select(
                        new QBrandDao(
                                brandEntity.id,
                                brandEntity.brandName,
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

        return toDomain(brands);
    }

    private List<Brand> toDomain(List<BrandDao> brands) {
        return brands.stream().map(BrandDao::toBrand).toList();
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
