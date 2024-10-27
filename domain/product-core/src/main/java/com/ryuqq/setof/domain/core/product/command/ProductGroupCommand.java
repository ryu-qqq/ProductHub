package com.ryuqq.setof.domain.core.product.command;

import com.ryuqq.setof.core.ManagementType;
import com.ryuqq.setof.core.OptionType;
import com.ryuqq.setof.core.ProductCondition;
import com.ryuqq.setof.core.ProductStatus;
import com.ryuqq.setof.domain.core.generic.Money;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record ProductGroupCommand(
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
        ProductStatus productStatus
) {

    public ProductGroupEntity toEntity(){
        return new ProductGroupEntity(
                sellerId, categoryId, brandId, productGroupName, styleCode, productCondition, managementType,
                optionType, regularPrice, currentPrice, calculateDiscountRate(regularPrice, currentPrice),
                soldOutYn, displayYn, productStatus
        );
    }

    private int calculateDiscountRate(BigDecimal regularPrice, BigDecimal currentPrice) {
        Money regularMoney = Money.wons(regularPrice);
        Money currentMoney = Money.wons(currentPrice);

        Money discount = regularMoney.minus(currentMoney);
        BigDecimal discountRate = discount.divide(regularMoney, 2, RoundingMode.HALF_UP);
        return discountRate.multiply(BigDecimal.valueOf(100)).intValueExact();
    }

}
