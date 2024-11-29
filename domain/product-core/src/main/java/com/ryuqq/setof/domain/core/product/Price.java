package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.support.utils.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Price {

    private Money regularPrice;
    private Money currentPrice;
    private Money salePrice;
    private Money directDiscountPrice;
    private int directDiscountRate;
    private int discountRate;

    protected Price(){}

    public Price(BigDecimal regularPrice, BigDecimal currentPrice) {
        this.regularPrice = Money.wons(regularPrice);
        this.currentPrice = Money.wons(currentPrice);
        this.salePrice = Money.wons(currentPrice);
        this.discountRate = calculateDiscountRate(Money.wons(regularPrice), Money.wons(currentPrice));
        this.directDiscountPrice = Money.ZERO;
        this.directDiscountRate = 0;
    }

    private int calculateDiscountRate(Money basePrice, Money salePrice) {
        if (basePrice.isLessThan(Money.wons(1))) {
            throw new IllegalArgumentException("Base price cannot be zero.");
        }

        Money discount = basePrice.minus(salePrice);
        BigDecimal discountRate = discount.divide(basePrice, 2, RoundingMode.HALF_UP); // Money 객체의 divide 메서드 사용
        return discountRate.multiply(BigDecimal.valueOf(100)).intValueExact();
    }


    public Money getRegularPrice() {
        return regularPrice;
    }

    public Money getCurrentPrice() {
        return currentPrice;
    }

    public Money getSalePrice() {
        return salePrice;
    }

    public Money getDirectDiscountPrice() {
        return directDiscountPrice;
    }

    public int getDirectDiscountRate() {
        return directDiscountRate;
    }

    public int getDiscountRate() {
        return discountRate;
    }


}
