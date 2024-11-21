package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.Origin;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductNoticeChecker implements UpdateChecker<ProductNotice, ProductNoticeCommand>{

    @Override
    public UpdateDecision checkUpdates(ProductNotice existing, ProductNoticeCommand updated) {
        UpdateDecision decision = new UpdateDecision();

        if (isUpdateMaterial(existing, updated.material()) ||
                isUpdateColor(existing, updated.color()) ||
                isUpdateSize(existing, updated.size()) ||
                isUpdateMaker(existing, updated.maker()) ||
                isUpdateOrigin(existing, updated.origin()) ||
                isUpdateWashingMethod(existing, updated.washingMethod()) ||
                isUpdateYearMonth(existing, updated.yearMonth()) ||
                isUpdateAssuranceStandard(existing, updated.assuranceStandard()) ||
                isUpdateAsPhone(existing, updated.asPhone())) {

            decision.addBatchUpdate(updated.toEntity(existing.getProductGroupId()));
        }

        return decision;
    }

    private boolean isUpdateMaterial(ProductNotice existing, String newMaterial) {
        return !Objects.equals(existing.getMaterial(), newMaterial);
    }

    private boolean isUpdateColor(ProductNotice existing, String newColor) {
        return !Objects.equals(existing.getColor(), newColor);
    }

    private boolean isUpdateSize(ProductNotice existing, String newSize) {
        return !Objects.equals(existing.getSize(), newSize);
    }

    private boolean isUpdateMaker(ProductNotice existing, String newMaker) {
        return !Objects.equals(existing.getMaker(), newMaker);
    }

    private boolean isUpdateOrigin(ProductNotice existing, Origin newOrigin) {
        return existing.getOrigin() != newOrigin;
    }

    private boolean isUpdateWashingMethod(ProductNotice existing, String newWashingMethod) {
        return !Objects.equals(existing.getWashingMethod(), newWashingMethod);
    }

    private boolean isUpdateYearMonth(ProductNotice existing, String newYearMonth) {
        return !Objects.equals(existing.getYearMonth(), newYearMonth);
    }

    private boolean isUpdateAssuranceStandard(ProductNotice existing, String newAssuranceStandard) {
        return !Objects.equals(existing.getAssuranceStandard(), newAssuranceStandard);
    }

    private boolean isUpdateAsPhone(ProductNotice existing, String newAsPhone) {
        return !Objects.equals(existing.getAsPhone(), newAsPhone);
    }



}
