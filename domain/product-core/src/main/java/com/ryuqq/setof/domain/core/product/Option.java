package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.core.OptionName;
import com.ryuqq.setof.storage.db.core.product.option.detail.OptionDetailEntity;
import com.ryuqq.setof.storage.db.core.product.option.group.OptionGroupEntity;

import java.util.Objects;

public class Option {
    private final Long productId;
    private final Long optionGroupId;
    private final Long optionDetailId;
    private final OptionName optionName;
    private final String optionValue;
    private boolean deleteYn;

    public Option(Long productId, Long optionGroupId, Long optionDetailId, OptionName optionName, String optionValue) {
        this.productId = productId;
        this.optionGroupId = optionGroupId;
        this.optionDetailId = optionDetailId;
        this.optionName = optionName;
        this.optionValue = optionValue;
        this.deleteYn = false;
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

    public boolean isDeleteYn() {
        return deleteYn;
    }

    public void delete(){
        this.deleteYn = true;
    }

    public boolean requiresUpdate(OptionCommand command) {
        return !this.getOptionName().equals(command.name()) ||
                !this.getOptionValue().equals(command.value());
    }

    public OptionGroupEntity toGroupEntity() {
        return new OptionGroupEntity(
                this.optionGroupId, this.optionName, this.deleteYn);
    }

    public OptionDetailEntity toDetailEntity() {
        return new OptionDetailEntity(
                this.optionDetailId , this.optionGroupId, this.optionValue, this.deleteYn);
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
                Objects.equals(optionValue, that.optionValue) &&
                Objects.equals(deleteYn, that.deleteYn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, optionGroupId, optionDetailId, optionName, optionValue, deleteYn);
    }

    @Override
    public String toString() {
        return "Option{" +
                "productId=" + productId +
                ", optionGroupId=" + optionGroupId +
                ", optionDetailId=" + optionDetailId +
                ", optionName=" + optionName +
                ", optionValue='" + optionValue + '\'' +
                ", deleteYn=" + deleteYn +
                '}';
    }
}
