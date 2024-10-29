package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.generic.Money;

import java.math.BigDecimal;

public class Price {

    private Money regularPrice;
    private Money currentPrice;
    private Money salePrice;
    private Money directDiscountPrice;
    private int directDiscountRate;
    private int discountRate;

    protected Price(){}

    public Price(BigDecimal regularPrice, BigDecimal currentPrice, int discountRate) {
        this.regularPrice = Money.wons(regularPrice);
        this.currentPrice = Money.wons(currentPrice);
        this.salePrice = Money.wons(currentPrice);
        this.discountRate = discountRate;
        this.directDiscountPrice = Money.ZERO;
        this.directDiscountRate = 0;
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
