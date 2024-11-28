package com.ryuqq.setof.storage.db.core.site.external;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.storage.db.core.site.external.dto.*;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.product.group.QProductGroupEntity.productGroupEntity;
import static com.ryuqq.setof.storage.db.core.site.external.QExternalPolicyEntity.externalPolicyEntity;
import static com.ryuqq.setof.storage.db.core.site.external.QExternalProcessingResultEntity.externalProcessingResultEntity;
import static com.ryuqq.setof.storage.db.core.site.external.QExternalProductEntity.externalProductEntity;
import static com.ryuqq.setof.storage.db.core.site.external.QExternalProductPolicyEntity.externalProductPolicyEntity;

@Repository
public class ExternalProductPolicyQueryDslRepository implements ExternalProductPolicyQueryRepository{

    private final JPAQueryFactory queryFactory;


    public ExternalProductPolicyQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<ExternalProductPolicyDto> fetchByFilter(ExternalProductFilterDto externalProductFilterDto) {
        return queryFactory.select(
                        new QExternalProductPolicyDto(
                                externalPolicyEntity.siteId,
                                externalPolicyEntity.id,
                                externalProductPolicyEntity.countryCode,
                                externalProductPolicyEntity.translated
                        )
                )
                .from(externalPolicyEntity)
                .innerJoin(externalProductPolicyEntity)
                    .on(externalProductPolicyEntity.policyId.eq(externalPolicyEntity.id))
                .where(externalPolicyEntity.siteId.in(siteIds))
                .fetch();
    }



    @Override
    public List<ExternalProductContextDto> fetchExternalProductContextByFilter(ExternalProductFilterDto externalProductFilterDto){
        return queryFactory
                .from(externalProductEntity)
                .leftJoin(productGroupEntity)
                    .on(externalProductEntity.productGroupId.eq(productGroupEntity.id))
                .leftJoin(externalPolicyEntity)
                    .on(externalPolicyEntity.siteId.eq(externalProductEntity.policyId))
                .leftJoin(externalProductPolicyEntity)
                    .on(externalProductPolicyEntity.policyId.eq(externalPolicyEntity.id))
                .leftJoin(externalProcessingResultEntity)
                    .on(externalProcessingResultEntity.productGroupId.eq(externalProductEntity.productGroupId))
                .where(
                        siteIdEq(externalProductFilterDto.siteId()),
                        sellerIdEq(externalProductFilterDto.sellerId()),
                        productGroupIdIn(externalProductFilterDto.productGroupIds()),
                        statusEq(externalProductFilterDto.status()),
                        externalProductIdGt(externalProductFilterDto.cursorId())
                )
                .limit(externalProductFilterDto.pageSize())
                .orderBy(externalProductEntity.id.asc())
                .transform(
                        GroupBy.groupBy(externalProductEntity.id).list(
                                new QExternalProductContextDto(
                                        new QExternalProductDto(
                                                externalProductEntity.id,
                                                externalProductEntity.siteId,
                                                externalProductEntity.productGroupId,
                                                externalProductEntity.externalProductId.coalesce(""),
                                                externalProductEntity.policyId,
                                                externalProductEntity.status
                                        ),
                                        new QExternalProductPolicyDto(
                                                externalProductEntity.siteId,
                                                externalProductPolicyEntity.policyId,
                                                externalProductPolicyEntity.countryCode,
                                                externalProductPolicyEntity.translated
                                        ),
                                        GroupBy.set(
                                                new QExternalProductProcessingResultDto(
                                                        externalProcessingResultEntity.productGroupId,
                                                        externalProcessingResultEntity.productDataType,
                                                        externalProcessingResultEntity.result
                                                )
                                        )
                                )
                        )
                );
    }


    private BooleanExpression productGroupIdIn(List<Long> productGroupIds) {
        if(productGroupIds == null || productGroupIds.isEmpty()) return null;
        return externalProductEntity.productGroupId.in(productGroupIds);
    }

    private BooleanExpression siteIdEq(Long siteId) {
        if(siteId != null) return externalProductEntity.siteId.eq(siteId);
        else return null;
    }

    private BooleanExpression sellerIdEq(Long sellerId) {
        if(sellerId != null) return productGroupEntity.sellerId.eq(sellerId);
        else return null;
    }

    private BooleanExpression statusEq(SyncStatus status) {
        if(status == null ) return null;
        return externalProductEntity.status.eq(status);
    }

    private BooleanExpression externalProductIdGt(Long cursorId) {
        if(cursorId == null ) return null;
        return externalProductEntity.id.gt(cursorId);
    }


}
