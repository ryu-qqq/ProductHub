package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.Origin;

import java.util.Objects;

public class ProductNoticeUpdater {

    private final ProductNotice productNotice;

    public ProductNoticeUpdater(ProductNotice productNotice) {
        this.productNotice = productNotice;
    }

    public boolean hasUpdates(ProductNoticeCommand command) {
        return isUpdateMaterial(command.material()) ||
                isUpdateColor(command.color()) ||
                isUpdateSize(command.size()) ||
                isUpdateMaker(command.maker()) ||
                isUpdateOrigin(command.origin()) ||
                isUpdateWashingMethod(command.washingMethod()) ||
                isUpdateYearMonth(command.yearMonth()) ||
                isUpdateAssuranceStandard(command.assuranceStandard()) ||
                isUpdateAsPhone(command.asPhone());
    }

    public boolean isUpdateMaterial(String newMaterial) {
        return !Objects.equals(productNotice.getMaterial(), newMaterial);
    }

    public boolean isUpdateColor(String newColor) {
        return !Objects.equals(productNotice.getColor(), newColor);
    }

    public boolean isUpdateSize(String newSize) {
        return !Objects.equals(productNotice.getSize(), newSize);
    }

    public boolean isUpdateMaker(String newMaker) {
        return !Objects.equals(productNotice.getMaker(), newMaker);
    }

    public boolean isUpdateOrigin(Origin newOrigin) {
        return productNotice.getOrigin() != newOrigin;
    }

    public boolean isUpdateWashingMethod(String newWashingMethod) {
        return !Objects.equals(productNotice.getWashingMethod(), newWashingMethod);
    }

    public boolean isUpdateYearMonth(String newYearMonth) {
        return !Objects.equals(productNotice.getYearMonth(), newYearMonth);
    }

    public boolean isUpdateAssuranceStandard(String newAssuranceStandard) {
        return !Objects.equals(productNotice.getAssuranceStandard(), newAssuranceStandard);
    }

    public boolean isUpdateAsPhone(String newAsPhone) {
        return !Objects.equals(productNotice.getAsPhone(), newAsPhone);
    }


}
