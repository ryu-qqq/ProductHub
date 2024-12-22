package com.ryuqq.setof.storage.db.core.product.option;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.product.dto.ProductContextDto;
import com.ryuqq.setof.storage.db.core.product.dto.QProductContextDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

import static com.ryuqq.setof.storage.db.core.product.option.QProductEntity.productEntity;
import static com.ryuqq.setof.storage.db.core.product.option.detail.QOptionDetailEntity.optionDetailEntity;
import static com.ryuqq.setof.storage.db.core.product.option.group.QOptionGroupEntity.optionGroupEntity;
import static com.ryuqq.setof.storage.db.core.product.option.mapping.QProductOptionEntity.productOptionEntity;

@Repository
public class ProductQueryDslQueryRepository implements ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ProductQueryDslQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ProductContextDto> fetchByProductGroupIds(List<Long> productGroupIds) {
        return queryFactory
                .select(
                        new QProductContextDto(
                                productEntity.productGroupId,
                                productEntity.id,
                                productEntity.quantity,
                                productEntity.displayYn,
                                productEntity.soldOutYn,
                                optionGroupEntity.id,
                                optionDetailEntity.id,
                                optionGroupEntity.optionName,
                                optionDetailEntity.optionValue,
                                productEntity.additionalPrice.coalesce(BigDecimal.ZERO)
                        )
                )
                .from(productEntity)
                .leftJoin(productOptionEntity)
                    .on(productOptionEntity.productId.eq(productEntity.id))
                    .on(productOptionEntity.deleteYn.eq(false))
                .leftJoin(optionGroupEntity)
                    .on(optionGroupEntity.id.eq(productOptionEntity.optionGroupId))
                    .on(optionGroupEntity.deleteYn.eq(false))
                .leftJoin(optionDetailEntity)
                    .on(optionDetailEntity.id.eq(productOptionEntity.optionDetailId))
                    .on(optionDetailEntity.deleteYn.eq(false))
                .where(productGroupIdIn(productGroupIds), productEntity.deleteYn.eq(false))
                .orderBy(productEntity.id.asc())
                .fetch();
    }


    private BooleanExpression productGroupIdIn(List<Long> productGroupIds) {
        return productEntity.productGroupId.in(productGroupIds);
    }


}
