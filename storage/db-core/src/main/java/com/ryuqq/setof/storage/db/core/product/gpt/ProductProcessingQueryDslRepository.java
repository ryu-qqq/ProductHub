package com.ryuqq.setof.storage.db.core.product.gpt;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.storage.db.core.product.dto.ProductProcessingResultDto;
import com.ryuqq.setof.storage.db.core.product.dto.QProductProcessingResultDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ryuqq.setof.storage.db.core.product.gpt.QProductProcessingResultEntity.productProcessingResultEntity;


@Repository
public class ProductProcessingQueryDslRepository implements ProductProcessingQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ProductProcessingQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<ProductProcessingResultDto> fetchByProductGroupIdAndDataType(long productGroupId, ProductDataType productDataType) {
       return Optional.ofNullable(
                queryFactory.select(
                            new QProductProcessingResultDto(
                                    productProcessingResultEntity.productGroupId,
                                    productProcessingResultEntity.productDataType,
                                    productProcessingResultEntity.result
                            )
                        )
                        .from(productProcessingResultEntity)
                        .where(
                                productGroupIdEq(productGroupId),
                                productDataTypeEq(productDataType)
                        )
                        .orderBy(productProcessingResultEntity.id.desc())
                        .fetchOne()
        );
    }

    @Override
    public List<ProductProcessingResultDto> fetchByProductGroupIdsAndDataType(List<Long> productGroupIds, ProductDataType productDataType) {
        return queryFactory.select(
                                new QProductProcessingResultDto(
                                        productProcessingResultEntity.productGroupId,
                                        productProcessingResultEntity.productDataType,
                                        productProcessingResultEntity.result
                                )
                        )
                        .from(productProcessingResultEntity)
                        .where(
                                productGroupIdIn(productGroupIds),
                                productDataTypeEq(productDataType)
                        )
                        .orderBy(productProcessingResultEntity.id.desc())
                        .fetch();
    }

    private BooleanExpression productGroupIdEq(long productGroupId){
        return productProcessingResultEntity.productGroupId.eq(productGroupId);
    }

    private BooleanExpression productGroupIdIn(List<Long> productGroupIds){
        if(!productGroupIds.isEmpty()) return productProcessingResultEntity.productGroupId.in(productGroupIds);
        return null;
    }

    private BooleanExpression productDataTypeEq(ProductDataType productDataType){
        return productProcessingResultEntity.productDataType.eq(productDataType);
    }
}
