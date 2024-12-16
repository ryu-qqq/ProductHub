package com.ryuqq.setof.storage.db.core.site.external;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductGroupDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductGroupStorageFilterDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.QExternalProductGroupDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.QExternalProductImageDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.ryuqq.setof.storage.db.core.brand.QMappingBrandEntity.mappingBrandEntity;
import static com.ryuqq.setof.storage.db.core.category.QCategoryEntity.categoryEntity;
import static com.ryuqq.setof.storage.db.core.category.QMappingCategoryEntity.mappingCategoryEntity;
import static com.ryuqq.setof.storage.db.core.product.group.QProductGroupEntity.productGroupEntity;
import static com.ryuqq.setof.storage.db.core.site.QSiteEntity.siteEntity;
import static com.ryuqq.setof.storage.db.core.site.external.QExternalProductGroupEntity.externalProductGroupEntity;
import static com.ryuqq.setof.storage.db.core.site.external.QExternalProductImageEntity.externalProductImageEntity;

@Repository
public class ExternalProductGroupGroupQueryDslRepository implements ExternalProductGroupQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ExternalProductGroupGroupQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public long countByFilter(ExternalProductGroupStorageFilterDto filter) {
        Long count = queryFactory
                .select(
                        externalProductGroupEntity.count()
                )
                .from(externalProductGroupEntity)
                .innerJoin(productGroupEntity)
                    .on(externalProductGroupEntity.productGroupId.eq(productGroupEntity.id))
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
        return queryFactory.selectFrom(externalProductGroupEntity)
                .where(externalProductGroupEntity.siteId.in(siteIds)
                        .and(externalProductGroupEntity.productGroupId.eq(productGroupEntity.id)))
                .notExists();
    }

    @Override
    public List<Long> fetchIdsByFilter(ExternalProductGroupStorageFilterDto filter){
        return queryFactory
                .select(externalProductGroupEntity.id)
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
                )
                .orderBy(externalProductGroupEntity.id.desc())
                .limit(filter.pageSize() + 1)
                .fetch();
    }


    @Override
    public List<ExternalProductGroupDto> fetchByIds(List<Long> externalProductIds){
        return queryFactory
                .from(externalProductGroupEntity)
                .innerJoin(siteEntity)
                    .on(siteEntity.id.eq(externalProductGroupEntity.siteId))
                .innerJoin(productGroupEntity)
                    .on(externalProductGroupEntity.productGroupId.eq(productGroupEntity.id))
                .innerJoin(categoryEntity)
                    .on(categoryEntity.id.eq(productGroupEntity.categoryId))
                .leftJoin(mappingBrandEntity)
                    .on(mappingBrandEntity.internalBrandId.eq(productGroupEntity.brandId))
                    .on(mappingBrandEntity.siteId.eq(externalProductGroupEntity.siteId))
                .leftJoin(mappingCategoryEntity)
                    .on(mappingCategoryEntity.internalCategoryId.eq(productGroupEntity.categoryId))
                    .on(mappingCategoryEntity.siteId.eq(externalProductGroupEntity.siteId))
                .leftJoin(externalProductImageEntity)
                    .on(externalProductImageEntity.productGroupId.eq(externalProductGroupEntity.productGroupId))
                    .on(externalProductImageEntity.siteId.eq(siteEntity.id))
                    .on(externalProductImageEntity.deleteYn.eq(false))
                .orderBy(externalProductGroupEntity.id.desc())
                .where(
                        externalProductIds(externalProductIds)
                ).transform(
                        GroupBy.groupBy(externalProductGroupEntity.id).list(
                                new QExternalProductGroupDto(
                                        externalProductGroupEntity.id,
                                        externalProductGroupEntity.siteId,
                                        siteEntity.name,
                                        externalProductGroupEntity.productGroupId,
                                        externalProductGroupEntity.policyId,
                                        externalProductGroupEntity.externalProductGroupId.coalesce(""),
                                        externalProductGroupEntity.productName.coalesce(""),
                                        productGroupEntity.productGroupName,
                                        externalProductGroupEntity.regularPrice.coalesce(BigDecimal.TEN),
                                        externalProductGroupEntity.currentPrice.coalesce(BigDecimal.TEN),
                                        externalProductGroupEntity.status,
                                        externalProductGroupEntity.soldOutYn,
                                        externalProductGroupEntity.displayYn,
                                        productGroupEntity.sellerId,
                                        productGroupEntity.brandId,
                                        productGroupEntity.categoryId,
                                        categoryEntity.path,
                                        mappingBrandEntity.siteBrandId.coalesce(""),
                                        mappingCategoryEntity.siteCategoryId.coalesce(""),
                                        mappingCategoryEntity.siteCategoryExtraId.coalesce(""),
                                        externalProductGroupEntity.insertTime,
                                        externalProductGroupEntity.updateTime,
                                        GroupBy.list(
                                                new QExternalProductImageDto(
                                                        externalProductImageEntity.productGroupId,
                                                        externalProductImageEntity.siteId,
                                                        externalProductImageEntity.externalProductGroupId,
                                                        externalProductImageEntity.displayOrder,
                                                        externalProductImageEntity.imageUrl,
                                                        externalProductImageEntity.originUrl
                                                )
                                        )
                                )
                        )
                );
    }

    private BooleanExpression productGroupIdIn(List<Long> productGroupIds){
        if(!productGroupIds.isEmpty()){
            return productGroupEntity.id.in(productGroupIds);
        }
        return null;
    }

    private BooleanExpression siteIdEq(Long siteId) {
        if(siteId != null) return externalProductGroupEntity.siteId.eq(siteId);
        else return null;
    }

    private BooleanExpression sellerIdEq(Long sellerId) {
        if(sellerId != null) return productGroupEntity.sellerId.eq(sellerId);
        else return null;
    }

    private BooleanExpression statusEq(SyncStatus status) {
        if(status == null ) return null;
        return externalProductGroupEntity.status.eq(status);
    }

    private BooleanExpression externalProductIdLt(Long cursorId) {
        if(cursorId == null ) return null;
        return externalProductGroupEntity.id.lt(cursorId);
    }


    private BooleanExpression soldOutEq(Boolean soldOutYn) {
        if(soldOutYn == null) return null;
        return externalProductGroupEntity.soldOutYn.eq(soldOutYn);
    }

    private BooleanExpression displayEq(Boolean displayYn) {
        if(displayYn == null) return null;
        return externalProductGroupEntity.displayYn.eq(displayYn);
    }

    private BooleanExpression betweenTime(LocalDateTime start, LocalDateTime end){
        if(start == null || end == null) return null;
        return externalProductGroupEntity.updateTime.between(start, end);
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

    private BooleanExpression externalProductIds(List<Long> externalProductIds) {
        return externalProductGroupEntity.id.in(externalProductIds);
    }



}
