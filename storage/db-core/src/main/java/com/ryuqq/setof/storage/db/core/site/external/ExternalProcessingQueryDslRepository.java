package com.ryuqq.setof.storage.db.core.site.external;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductProcessingResultDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.QExternalProductProcessingResultDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.ryuqq.setof.storage.db.core.site.external.QExternalProcessingResultEntity.externalProcessingResultEntity;

@Repository
public class ExternalProcessingQueryDslRepository implements ExternalProcessingQueryRepository{

    private final JPAQueryFactory queryFactory;

    public ExternalProcessingQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<ExternalProductProcessingResultDto> fetchByProductGroupIdAndDataType(long productGroupId, ProductDataType productDataType) {
       return Optional.ofNullable(
                queryFactory.select(
                            new QExternalProductProcessingResultDto(
                                    externalProcessingResultEntity.productGroupId,
                                    externalProcessingResultEntity.productDataType,
                                    externalProcessingResultEntity.result
                            )
                        )
                        .from(externalProcessingResultEntity)
                        .where(
                                productGroupIdEq(productGroupId),
                                productDataTypeEq(productDataType)
                        )
                        .orderBy(externalProcessingResultEntity.id.desc())
                        .limit(1)
                        .fetchOne()
        );
    }

    private BooleanExpression productGroupIdEq(long productGroupId){
        return externalProcessingResultEntity.productGroupId.eq(productGroupId);
    }

    private BooleanExpression productDataTypeEq(ProductDataType productDataType){
        return externalProcessingResultEntity.productDataType.eq(productDataType);
    }
}
