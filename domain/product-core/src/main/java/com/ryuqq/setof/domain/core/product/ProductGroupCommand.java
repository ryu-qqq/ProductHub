package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.ManagementType;
import com.ryuqq.setof.enums.core.OptionType;
import com.ryuqq.setof.enums.core.ProductCondition;
import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.support.utils.Money;
import com.ryuqq.setof.db.core.product.group.ProductGroupEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record ProductGroupCommand(
        long setofProductGroupId,
        long brandId,
        long categoryId,
        long sellerId,
        String productGroupName,
        String styleCode,
        ProductCondition productCondition,
        ManagementType managementType,
        OptionType optionType,
        BigDecimal regularPrice,
        BigDecimal currentPrice,
        boolean soldOutYn,
        boolean displayYn,
        ProductStatus productStatus,
        String keywords
) {

    public ProductGroupEntity toEntity(){
        ProductGroupEntity productGroupEntity = new ProductGroupEntity(
                sellerId, categoryId, brandId, productGroupName, styleCode, productCondition, managementType,
                optionType, regularPrice, currentPrice, calculateDiscountRate(regularPrice, currentPrice),
                soldOutYn, displayYn, productStatus, keywords
        );
        productGroupEntity.setSetofProductGroupId(setofProductGroupId);
        return productGroupEntity;
    }

    public ProductGroupEntity toEntity(long productGroupId){
        ProductGroupEntity productGroupEntity = new ProductGroupEntity(productGroupId,
                sellerId, categoryId, brandId, productGroupName, styleCode, productCondition, managementType,
                optionType, regularPrice, currentPrice, calculateDiscountRate(regularPrice, currentPrice),
                soldOutYn, displayYn, productStatus, keywords
        );
        productGroupEntity.setSetofProductGroupId(setofProductGroupId);
        return productGroupEntity;
    }

    private int calculateDiscountRate(BigDecimal regularPrice, BigDecimal currentPrice) {
        Money regularMoney = Money.wons(regularPrice);
        Money currentMoney = Money.wons(currentPrice);

        Money discount = regularMoney.minus(currentMoney);
        BigDecimal discountRate = discount.divide(regularMoney, 2, RoundingMode.HALF_UP);
        return discountRate.multiply(BigDecimal.valueOf(100)).intValueExact();
    }

}
