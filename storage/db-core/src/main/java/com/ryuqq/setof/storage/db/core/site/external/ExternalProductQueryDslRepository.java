package com.ryuqq.setof.storage.db.core.site.external;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.enums.core.ManagementType;
import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.storage.db.core.brand.QMappingBrandEntity;
import com.ryuqq.setof.storage.db.core.category.QCategoryEntity;
import com.ryuqq.setof.storage.db.core.category.QMappingCategoryEntity;
import com.ryuqq.setof.storage.db.core.site.QSiteEntity;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductStorageFilterDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.QExternalProductDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.ryuqq.setof.storage.db.core.brand.QMappingBrandEntity.mappingBrandEntity;
import static com.ryuqq.setof.storage.db.core.category.QCategoryEntity.categoryEntity;
import static com.ryuqq.setof.storage.db.core.category.QMappingCategoryEntity.mappingCategoryEntity;
import static com.ryuqq.setof.storage.db.core.product.group.QProductGroupEntity.productGroupEntity;
import static com.ryuqq.setof.storage.db.core.site.QSiteEntity.siteEntity;
import static com.ryuqq.setof.storage.db.core.site.external.QExternalProductEntity.externalProductEntity;

@Repository
public class ExternalProductQueryDslRepository implements ExternalProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ExternalProductQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public long countByFilter(ExternalProductStorageFilterDto filter) {
        Long count = queryFactory
                .select(
                        externalProductEntity.count()
                )
                .from(externalProductEntity)
                .innerJoin(productGroupEntity)
                    .on(externalProductEntity.productGroupId.eq(productGroupEntity.id))
                .where(
                        siteIdEq(filter.siteId()),
                        betweenTime(filter.startDate(), filter.endDate()),
                        soldOutEq(filter.soldOutYn()),
                        displayEq(filter.displayYn()),
                        sellerIdEq(filter.sellerId()),
                        betweenPrice(filter.minSalePrice(), filter.maxSalePrice()),
                        betweenSalePercent(filter.minDiscountRate(), filter.maxDiscountRate()),
                        categoryIn(filter.categoryIds()),
                        brandIdIn(filter.brandIds()),
                        styleCodeEq(filter.styleCode()),
                        statusEq(filter.syncStatus()),
                        externalProductIdLt(filter.cursorId()),
                        productGroupIdIn(filter.productGroupIds())
                ).fetchOne();

        return count !=null ? count : 0;
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

    @Override
    public List<ExternalProductDto> fetchByFilter(ExternalProductStorageFilterDto filter){
        return queryFactory
                .select(
                        new QExternalProductDto(
                                externalProductEntity.id,
                                externalProductEntity.siteId,
                                siteEntity.name,
                                externalProductEntity.productGroupId,
                                externalProductEntity.policyId,
                                externalProductEntity.externalProductId.coalesce(""),
                                externalProductEntity.productName.coalesce(""),
                                externalProductEntity.regularPrice.coalesce(BigDecimal.TEN),
                                externalProductEntity.currentPrice.coalesce(BigDecimal.TEN),
                                externalProductEntity.status,
                                externalProductEntity.soldOutYn,
                                externalProductEntity.displayYn,
                                productGroupEntity.sellerId,
                                productGroupEntity.brandId,
                                productGroupEntity.categoryId,
                                categoryEntity.path,
                                mappingBrandEntity.siteBrandId.coalesce(""),
                                mappingCategoryEntity.siteCategoryId.coalesce(""),
                                externalProductEntity.insertTime,
                                externalProductEntity.lastSyncTime
                        )
                )
                .from(externalProductEntity)
                .innerJoin(siteEntity)
                    .on(siteEntity.id.eq(externalProductEntity.siteId))
                .innerJoin(productGroupEntity)
                    .on(externalProductEntity.productGroupId.eq(productGroupEntity.id))
                .innerJoin(categoryEntity)
                    .on(categoryEntity.id.eq(productGroupEntity.categoryId))
                .leftJoin(mappingBrandEntity)
                    .on(mappingBrandEntity.internalBrandId.eq(productGroupEntity.brandId))
                    .on(mappingBrandEntity.siteId.eq(externalProductEntity.siteId))
                .leftJoin(mappingCategoryEntity)
                    .on(mappingCategoryEntity.internalCategoryId.eq(productGroupEntity.categoryId))
                    .on(mappingCategoryEntity.siteId.eq(externalProductEntity.siteId))
                .orderBy(externalProductEntity.id.desc())
                .limit(filter.pageSize())
                .where(
                        siteIdEq(filter.siteId()),
                        betweenTime(filter.startDate(), filter.endDate()),
                        soldOutEq(filter.soldOutYn()),
                        displayEq(filter.displayYn()),
                        sellerIdEq(filter.sellerId()),
                        betweenPrice(filter.minSalePrice(), filter.maxSalePrice()),
                        betweenSalePercent(filter.minDiscountRate(), filter.maxDiscountRate()),
                        categoryIn(filter.categoryIds()),
                        brandIdIn(filter.brandIds()),
                        styleCodeEq(filter.styleCode()),
                        statusEq(filter.syncStatus()),
                        externalProductIdLt(filter.cursorId()),
                        productGroupIdIn(filter.productGroupIds())
                ).fetch();
    }

    private BooleanExpression productGroupIdIn(List<Long> productGroupIds){
        if(!productGroupIds.isEmpty()){
            return productGroupEntity.id.in(productGroupIds);
        }
        return null;
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

    private BooleanExpression externalProductIdLt(Long cursorId) {
        if(cursorId == null ) return null;
        return externalProductEntity.id.lt(cursorId);
    }


    private BooleanExpression soldOutEq(Boolean soldOutYn) {
        if(soldOutYn == null) return null;
        return productGroupEntity.soldOutYn.eq(soldOutYn);
    }

    private BooleanExpression displayEq(Boolean displayYn) {
        if(displayYn == null) return null;
        return productGroupEntity.displayYn.eq(displayYn);
    }

    private BooleanExpression betweenTime(LocalDateTime start, LocalDateTime end){
        if(start == null || end == null) return null;
        return productGroupEntity.insertTime.between(start, end);
    }


    private BooleanExpression betweenSalePercent(Long minDiscountRate, Long maxDiscountRate) {
        if(minDiscountRate!= null && maxDiscountRate != null && minDiscountRate >=0 && maxDiscountRate >0){
            return productGroupEntity.discountRate.between(minDiscountRate, maxDiscountRate);
        }
        return null;
    }

    private BooleanExpression styleCodeEq(String styleCode){
        if(styleCode != null && !styleCode.isBlank()) return productGroupEntity.styleCode.eq(styleCode);
        else return null;
    }


    private BooleanExpression categoryIn(List<Long> categoryIds) {
        if(!categoryIds.isEmpty()){
            return productGroupEntity.categoryId.in(categoryIds);
        }
        return null;
    }

    private BooleanExpression brandIdIn(List<Long> brandIds) {
        if(!brandIds.isEmpty()){
            return productGroupEntity.brandId.in(brandIds);
        }
        return null;
    }

    private BooleanExpression betweenPrice(Long minSalePrice, Long maxSalePrice) {
        if (minSalePrice != null && maxSalePrice != null && minSalePrice >= 0 && maxSalePrice > 0) {
            BigDecimal minPrice = BigDecimal.valueOf(minSalePrice);
            BigDecimal maxPrice = BigDecimal.valueOf(maxSalePrice);
            return productGroupEntity.currentPrice.between(minPrice, maxPrice);
        }

        return null;
    }




}
