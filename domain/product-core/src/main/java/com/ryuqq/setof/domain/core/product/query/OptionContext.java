package com.ryuqq.setof.domain.core.product.query;

import com.ryuqq.setof.core.OptionName;

import java.util.Objects;

public record OptionContext(
        Long optionGroupId,
        Long optionDetailId,
        OptionName optionName,
        String optionValue
) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OptionContext that = (OptionContext) obj;
        return  Objects.equals(optionGroupId, that.optionGroupId) &&
                Objects.equals(optionDetailId, that.optionDetailId) &&
                Objects.equals(optionName, that.optionName) &&
                Objects.equals(optionValue, that.optionValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionGroupId, optionDetailId, optionName, optionValue);
    }

    @Override
    public String toString() {
        return String.format("OptionGroupId : %d, optionDetailId : %d, " +
                "optionName : %s, optionValue : %s", optionGroupId, optionDetailId, optionName, optionValue);
    }

}
