package com.ryuqq.setof.storage.db.core.site.external;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.product.group.QProductGroupEntity.productGroupEntity;
import static com.ryuqq.setof.storage.db.core.site.external.QExternalProductEntity.externalProductEntity;

@Repository
public class ExternalProductQueryDslRepository implements ExternalProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ExternalProductQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Long> fetchUnlinkedProductGroupIdsBySellerIdAndSiteId(long sellerId, List<Long> siteIds) {
        return queryFactory
                .select(productGroupEntity.id)
                .from(productGroupEntity)
                .where(
                        productGroupEntity.sellerId.eq(sellerId),
                        notExistsInExternalProducts(siteIds)
                )
                .fetch();
    }




    private BooleanExpression notExistsInExternalProducts(List<Long> siteIds) {
        if (siteIds == null || siteIds.isEmpty()) {
            return null;
        }

        return queryFactory.selectFrom(externalProductEntity)
                .where(externalProductEntity.siteId.in(siteIds)
                        .and(externalProductEntity.productGroupId.eq(productGroupEntity.id)))
                .notExists();
    }

}
