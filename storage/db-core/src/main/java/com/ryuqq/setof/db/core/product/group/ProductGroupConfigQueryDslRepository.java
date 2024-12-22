package com.ryuqq.setof.storage.db.core.product.group;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupConfigDto;
import com.ryuqq.setof.storage.db.core.product.dto.QProductGroupConfigDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.product.group.QProductColorEntity.productColorEntity;
import static com.ryuqq.setof.storage.db.core.product.group.QProductGroupConfigEntity.productGroupConfigEntity;
import static com.ryuqq.setof.storage.db.core.product.group.QProductGroupEntity.productGroupEntity;

@Repository
public class ProductGroupConfigQueryDslRepository implements ProductGroupConfigQueryRepository{

    private final JPAQueryFactory queryFactory;

    public ProductGroupConfigQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ProductGroupConfigDto> fetchByConfigIds(List<Long> configIds){
        return queryFactory
                .select(
                        new QProductGroupConfigDto(
                                productGroupConfigEntity.id,
                                productGroupConfigEntity.productGroupId,
                                productGroupEntity.sellerId,
                                productGroupEntity.brandId,
                                productGroupEntity.categoryId,
                                productColorEntity.id,
                                productGroupConfigEntity.activeYn
                        )
                )
                .from(productGroupConfigEntity)
                .innerJoin(productGroupEntity)
                    .on(productGroupEntity.id.eq(productGroupConfigEntity.productGroupId))
                .leftJoin(productColorEntity)
                    .on(productGroupEntity.id.eq(productColorEntity.productGroupId))
                .where(
                        productGroupConfigIdIn(configIds)
                )
                .fetch();
    }

    @Override
    public List<ProductGroupConfigDto> fetchByProductGroupIds(List<Long> productGroupIds) {
        return queryFactory
                .select(
                        new QProductGroupConfigDto(
                                productGroupConfigEntity.id,
                                productGroupConfigEntity.productGroupId,
                                productGroupEntity.sellerId,
                                productGroupEntity.brandId,
                                productGroupEntity.categoryId,
                                productColorEntity.id,
                                productGroupConfigEntity.activeYn
                        )
                )
                .from(productGroupConfigEntity)
                .innerJoin(productGroupEntity)
                .on(productGroupEntity.id.eq(productGroupConfigEntity.productGroupId))
                .leftJoin(productColorEntity)
                .on(productGroupEntity.id.eq(productColorEntity.productGroupId))
                .where(
                        productGroupIdIn(productGroupIds)
                )
                .fetch();
    }


    private BooleanExpression productGroupIdIn(List<Long> productGroupIds) {
        return productGroupConfigEntity.productGroupId.in(productGroupIds);
    }

    private BooleanExpression productGroupConfigIdIn(List<Long> productGroupIds) {
        return productGroupConfigEntity.id.in(productGroupIds);
    }

}
