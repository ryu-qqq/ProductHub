package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.core.OptionName;

import java.util.Objects;

public class Option {
    private Long productId;
    private Long optionGroupId;
    private Long optionDetailId;
    private OptionName optionName;
    private String optionValue;

    public Option(Long productId, Long optionGroupId, Long optionDetailId, OptionName optionName, String optionValue) {
        this.productId = productId;
        this.optionGroupId = optionGroupId;
        this.optionDetailId = optionDetailId;
        this.optionName = optionName;
        this.optionValue = optionValue;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getOptionGroupId() {
        return optionGroupId;
    }

    public Long getOptionDetailId() {
        return optionDetailId;
    }

    public OptionName getOptionName() {
        return optionName;
    }

    public String getOptionValue() {
        return optionValue;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Option that = (Option) object;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(optionGroupId, that.optionGroupId) &&
                Objects.equals(optionDetailId, that.optionDetailId) &&
                optionName == that.optionName &&
                Objects.equals(optionValue, that.optionValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, optionGroupId, optionDetailId, optionName, optionValue);
    }

    @Override
    public String toString() {
        return "OptionContext{" +
                "productId=" + productId +
                ", optionGroupId=" + optionGroupId +
                ", optionDetailId=" + optionDetailId +
                ", optionName=" + optionName +
                ", optionValue='" + optionValue + '\'' +
                '}';
    }

}
