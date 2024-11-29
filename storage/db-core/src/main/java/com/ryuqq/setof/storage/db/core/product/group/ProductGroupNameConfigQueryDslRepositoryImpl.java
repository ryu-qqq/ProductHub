package com.ryuqq.setof.storage.db.core.product.group;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupNameConfigDto;
import com.ryuqq.setof.storage.db.core.product.dto.QProductGroupNameConfigDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.product.group.QProductGroupConfigEntity.productGroupConfigEntity;
import static com.ryuqq.setof.storage.db.core.product.group.QProductGroupNameConfigEntity.productGroupNameConfigEntity;

@Repository
public class ProductGroupNameConfigQueryDslRepositoryImpl implements ProductGroupNameConfigQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ProductGroupNameConfigQueryDslRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ProductGroupNameConfigDto> fetchByProductGroupIds(List<Long> configIds) {
        return queryFactory
                .select(
                        new QProductGroupNameConfigDto(
                                productGroupNameConfigEntity.id,
                                productGroupConfigEntity.id,
                                productGroupConfigEntity.productGroupId,
                                productGroupNameConfigEntity.countryCode,
                                productGroupNameConfigEntity.customName.coalesce("")
                        )
                )
                .from(productGroupConfigEntity)
                .innerJoin(productGroupNameConfigEntity)
                    .on(productGroupNameConfigEntity.productGroupConfigId.eq(productGroupConfigEntity.id))
                .where(configIdIn(configIds))
                .fetch();
    }

    private BooleanExpression configIdIn(List<Long> configIds) {
        return productGroupConfigEntity.id.in(configIds);
    }

}
