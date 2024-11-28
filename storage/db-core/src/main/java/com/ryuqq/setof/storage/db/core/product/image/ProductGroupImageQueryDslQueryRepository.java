package com.ryuqq.setof.storage.db.core.product.image;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupImageDto;
import com.ryuqq.setof.storage.db.core.product.dto.QProductGroupImageDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.product.image.QProductGroupImageEntity.productGroupImageEntity;

@Repository
public class ProductGroupImageQueryDslQueryRepository implements ProductGroupImageQueryRepository{
    private final JPAQueryFactory queryFactory;

    public ProductGroupImageQueryDslQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<ProductGroupImageDto> fetchByProductGroupId(long productGroupId){
        return queryFactory.select(
                    new QProductGroupImageDto(
                            productGroupImageEntity.id,
                            productGroupImageEntity.productGroupId,
                            productGroupImageEntity.productImageType,
                            productGroupImageEntity.imageUrl.coalesce(""),
                            productGroupImageEntity.originUrl.coalesce("")
                    )
                )
                .from(productGroupImageEntity)
                .where(
                        productGroupImageEntity.productGroupId.eq(productGroupId),
                        productGroupImageEntity.deleteYn.eq(false)
                )
                .fetch();
    }

}
