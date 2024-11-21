package com.ryuqq.setof.storage.db.core.product.group;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.enums.core.ManagementType;
import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.brand.dto.QBrandDto;
import com.ryuqq.setof.storage.db.core.category.dto.QCategoryDto;
import com.ryuqq.setof.storage.db.core.product.dto.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.ryuqq.setof.storage.db.core.brand.QBrandEntity.brandEntity;
import static com.ryuqq.setof.storage.db.core.category.QCategoryEntity.categoryEntity;
import static com.ryuqq.setof.storage.db.core.product.delivery.QProductDeliveryEntity.productDeliveryEntity;
import static com.ryuqq.setof.storage.db.core.product.description.QProductDetailDescriptionEntity.productDetailDescriptionEntity;
import static com.ryuqq.setof.storage.db.core.product.group.QProductGroupEntity.productGroupEntity;
import static com.ryuqq.setof.storage.db.core.product.image.QProductGroupImageEntity.productGroupImageEntity;
import static com.ryuqq.setof.storage.db.core.product.notice.QProductNoticeEntity.productNoticeEntity;

@Repository
public class ProductGroupQueryDslQueryRepository implements ProductGroupQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ProductGroupQueryDslQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ProductGroupEntity> fetchProductGroupEntitiesByStatus(ProductStatus productStatus, int pageSize){
        return queryFactory.selectFrom(productGroupEntity)
                .where(productStatusEq(productStatus))
                .limit(pageSize)
                .fetch();
    }


    @Override
    public List<Long> fetchProductGroupIds(ProductStatus productStatus, Long cursorId,  int pageSize) {
        return queryFactory.select(
                        productGroupEntity.id
                ).from(productGroupEntity)
                .where(
                        productStatusEq(productStatus),
                        isProductGroupIdGt(cursorId)
                        )
                .limit(pageSize)
                .fetch();
    }

    @Override
    public Optional<ProductStatus> fetchProductStatus(long productGroupId) {
        return Optional.ofNullable(
                queryFactory.select(
                                productGroupEntity.productStatus
                        ).from(productGroupEntity)
                            .where(productGroupIdEq(productGroupId))
                            .fetchOne()
        );
    }

    @Override
    public Optional<ProductGroupContextDto> fetchProductGroupContext(long productGroupId) {
        List<ProductGroupContextDto> productGroupContextDtos = fetchProductGroupContextsWithNoOffset(List.of(productGroupId));
        if(!productGroupContextDtos.isEmpty()) return Optional.of(productGroupContextDtos.getFirst());
        return Optional.empty();
    }

    @Override
    public List<ProductGroupContextDto> fetchProductGroupContexts(ProductGroupStorageFilterDto filter) {
        List<Long> productGroupIds = fetchProductGroupIdsWithNoOffset(filter);
        return fetchProductGroupContextsWithNoOffset(productGroupIds);
    }

    @Override
    public long fetchProductGroupCount(ProductGroupStorageFilterDto filter) {
        Long count = queryFactory.select(
                        productGroupEntity.count()
                )
                .from(productGroupEntity)
                .where(
                        productGroupIdIn(filter.productGroupIds()),
                        betweenTime(filter.startDate(), filter.endDate()),
                        soldOutEq(filter.soldOutYn()),
                        displayEq(filter.displayYn()),
                        sellerIdEq(filter.sellerId()),
                        productStatusEq(filter.productStatus()),
                        betweenPrice(filter.minSalePrice(), filter.maxSalePrice()),
                        betweenSalePercent(filter.minDiscountRate(), filter.maxDiscountRate()),
                        managementTypeEq(filter.managementType()),
                        categoryIn(filter.categoryIds()), brandIdIn(filter.brandIds()),
                        styleCodeEq(filter.styleCode())
                ).fetchOne();

        return count !=null ? count : 0;
    }


    private List<ProductGroupContextDto> fetchProductGroupContextsWithNoOffset(List<Long> productGroupIds) {
        return queryFactory
                .from(productGroupEntity)
                .innerJoin(brandEntity)
                    .on(brandEntity.id.eq(productGroupEntity.brandId))
                .innerJoin(categoryEntity)
                    .on(categoryEntity.id.eq(productGroupEntity.categoryId))
                .innerJoin(productNoticeEntity)
                    .on(productNoticeEntity.productGroupId.eq(productGroupEntity.id))
                .innerJoin(productDeliveryEntity)
                    .on(productDeliveryEntity.productGroupId.eq(productGroupEntity.id))
                .innerJoin(productGroupImageEntity)
                    .on(productGroupImageEntity.productGroupId.eq(productGroupEntity.id))
                    .on(productGroupImageEntity.deleteYn.eq(false))
                .innerJoin(productDetailDescriptionEntity)
                    .on(productDetailDescriptionEntity.productGroupId.eq(productGroupEntity.id))
                .where(productGroupIdIn(productGroupIds))
                .transform(GroupBy.groupBy(productGroupEntity.id).list(
                        new QProductGroupContextDto(
                                new QProductGroupDto(
                                        productGroupEntity.id,
                                        productGroupEntity.sellerId,
                                        new QCategoryDto(
                                                categoryEntity.id,
                                                categoryEntity.categoryName,
                                                categoryEntity.depth,
                                                categoryEntity.parentCategoryId,
                                                categoryEntity.displayYn,
                                                categoryEntity.targetGroup,
                                                categoryEntity.categoryType,
                                                categoryEntity.path
                                        ),
                                        new QBrandDto(
                                                brandEntity.id,
                                                brandEntity.brandName,
                                                brandEntity.brandNameKr,
                                                brandEntity.brandIconImageUrl.coalesce(""),
                                                brandEntity.displayYn
                                        ),
                                        productGroupEntity.productGroupName,
                                        productGroupEntity.styleCode,
                                        productGroupEntity.productCondition,
                                        productGroupEntity.managementType,
                                        productGroupEntity.optionType,
                                        productGroupEntity.regularPrice,
                                        productGroupEntity.currentPrice,
                                        productGroupEntity.discountRate,
                                        productGroupEntity.soldOutYn,
                                        productGroupEntity.displayYn,
                                        productGroupEntity.productStatus,
                                        productGroupEntity.keywords.coalesce("")
                                        ),
                                new QProductNoticeDto(
                                        productNoticeEntity.material,
                                        productNoticeEntity.color,
                                        productNoticeEntity.size,
                                        productNoticeEntity.maker,
                                        productNoticeEntity.origin,
                                        productNoticeEntity.washingMethod,
                                        productNoticeEntity.yearMonth,
                                        productNoticeEntity.assuranceStandard,
                                        productNoticeEntity.asPhone
                                ),
                                new QProductDeliveryDto(
                                        productDeliveryEntity.deliveryArea,
                                        productDeliveryEntity.deliveryFee,
                                        productDeliveryEntity.deliveryPeriodAverage,
                                        productDeliveryEntity.returnMethodDomestic,
                                        productDeliveryEntity.returnCourierDomestic,
                                        productDeliveryEntity.returnChargeDomestic,
                                        productDeliveryEntity.returnExchangeAreaDomestic
                                ),
                                GroupBy.list(
                                        new QProductGroupImageDto(
                                                productGroupImageEntity.id,
                                                productGroupImageEntity.productGroupId,
                                                productGroupImageEntity.productImageType,
                                                productGroupImageEntity.imageUrl.coalesce(""),
                                                productGroupImageEntity.originUrl.coalesce("")
                                        )
                                ),
                                new QProductDetailDescriptionDto(
                                        productDetailDescriptionEntity.detailDescription
                                )
                        )));
    }



    private List<Long> fetchProductGroupIdsWithNoOffset(ProductGroupStorageFilterDto filter){
        return queryFactory
                .select(productGroupEntity.id)
                .from(productGroupEntity)
                .where(
                        productGroupIdIn(filter.productGroupIds()),
                        betweenTime(filter.startDate(), filter.endDate()),
                        soldOutEq(filter.soldOutYn()),
                        displayEq(filter.displayYn()),
                        sellerIdEq(filter.sellerId()),
                        productStatusEq(filter.productStatus()),
                        betweenPrice(filter.minSalePrice(), filter.maxSalePrice()),
                        betweenSalePercent(filter.minDiscountRate(), filter.maxDiscountRate()),
                        managementTypeEq(filter.managementType()),
                        categoryIn(filter.categoryIds()), brandIdIn(filter.brandIds()),
                        isProductGroupIdLt(filter.cursorId()),
                        styleCodeEq(filter.styleCode())
                )
                .orderBy(productGroupEntity.id.desc())
                .limit(filter.pageSize())
                .fetch();
    }

    private BooleanExpression productGroupIdEq(long productGroupId) {
        return productGroupEntity.id.eq(productGroupId);
    }

    private BooleanExpression productGroupIdIn(List<Long> productGroupIds) {
        if(!productGroupIds.isEmpty()) return productGroupEntity.id.in(productGroupIds);
        else return null;
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

    private BooleanExpression managementTypeEq(ManagementType managementType) {
        return managementType !=null ? productGroupEntity.managementType.eq(managementType) : null;
    }

    private BooleanExpression isProductGroupIdLt(Long productGroupId){
        if(productGroupId !=null) return productGroupEntity.id.lt(productGroupId);
        else return null;
    }

    private BooleanExpression isProductGroupIdGt(Long productGroupId){
        if(productGroupId !=null) return productGroupEntity.id.gt(productGroupId);
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

    private BooleanExpression betweenSalePercent(Long minDiscountRate, Long maxDiscountRate) {
        if(minDiscountRate!= null && maxDiscountRate != null && minDiscountRate >=0 && maxDiscountRate >0){
            return productGroupEntity.discountRate.between(minDiscountRate, maxDiscountRate);
        }
        return null;
    }

    private BooleanExpression sellerIdEq(Long sellerId){
        if(sellerId != null) return productGroupEntity.sellerId.eq(sellerId);
        else return null;
    }

    private BooleanExpression styleCodeEq(String styleCode){
        if(styleCode != null && !styleCode.isBlank()) return productGroupEntity.styleCode.eq(styleCode);
        else return null;
    }

    private BooleanExpression productStatusEq(ProductStatus productStatus){
        if(productStatus != null) return productGroupEntity.productStatus.eq(productStatus);
        else return null;
    }

}
