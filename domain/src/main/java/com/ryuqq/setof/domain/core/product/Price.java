package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.generic.Money;

public class Price {

    private Money regularPrice;
    private Money currentPrice;
    private Money salePrice;
    private Money directDiscountPrice;
    private int directDiscountRate;
    private int discountRate;

    protected Price(){}

    private Price(Money regularPrice, Money currentPrice, Money salePrice, Money directDiscountPrice, int directDiscountRate, int discountRate) {
        this.regularPrice = regularPrice;
        this.currentPrice = currentPrice;
        this.salePrice = salePrice;
        this.directDiscountPrice = directDiscountPrice;
        this.directDiscountRate = directDiscountRate;
        this.discountRate = discountRate;
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

    public static class Builder {
        private Money regularPrice;
        private Money currentPrice;
        private Money salePrice;
        private Money directDiscountPrice;
        private int directDiscountRate;
        private int discountRate;

        public Builder regularPrice(Money regularPrice) {
            this.regularPrice = regularPrice;
            return this;
        }

        public Builder currentPrice(Money currentPrice) {
            this.currentPrice = currentPrice;
            return this;
        }

        public Builder salePrice(Money salePrice) {
            this.salePrice = salePrice;
            return this;
        }

        public Builder directDiscountPrice(Money directDiscountPrice) {
            this.directDiscountPrice = directDiscountPrice;
            return this;
        }

        public Builder directDiscountRate(int directDiscountRate) {
            this.directDiscountRate = directDiscountRate;
            return this;
        }

        public Builder discountRate(int discountRate) {
            this.discountRate = discountRate;
            return this;
        }

        public Price build() {
            return new Price(regularPrice, currentPrice, salePrice, directDiscountPrice, directDiscountRate, discountRate);
        }

    }

}
