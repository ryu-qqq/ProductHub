package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.support.utils.Money;
import com.ryuqq.setof.enums.core.ReturnMethod;
import com.ryuqq.setof.enums.core.ShipmentCompanyCode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class ProductDeliveryChecker implements UpdateChecker<ProductDelivery, ProductDeliveryCommand> {

    @Override
    public UpdateDecision checkUpdates(ProductDelivery existing, ProductDeliveryCommand updated) {
        UpdateDecision decision = new UpdateDecision();

        if (isUpdateDeliveryArea(existing, updated.deliveryArea()) ||
                isUpdateDeliveryFee(existing, updated.deliveryFee()) ||
                isUpdateDeliveryPeriodAverage(existing, updated.deliveryPeriodAverage()) ||
                isUpdateReturnMethodDomestic(existing, updated.returnMethodDomestic()) ||
                isUpdateReturnCourierDomestic(existing, updated.returnCourierDomestic()) ||
                isUpdateReturnChargeDomestic(existing, updated.returnChargeDomestic()) ||
                isUpdateReturnExchangeAreaDomestic(existing, updated.returnExchangeAreaDomestic())) {

            decision.addBatchUpdate(updated);
        }

        return decision;
    }

    private boolean isUpdateDeliveryArea(ProductDelivery existing, String newDeliveryArea) {
        return !Objects.equals(existing.getDeliveryArea(), newDeliveryArea);
    }

    private boolean isUpdateDeliveryFee(ProductDelivery existing, BigDecimal newDeliveryFee) {
        return !Objects.equals(existing.getDeliveryFee(), Money.wons(newDeliveryFee));
    }

    private boolean isUpdateDeliveryPeriodAverage(ProductDelivery existing, int newDeliveryPeriodAverage) {
        return existing.getDeliveryPeriodAverage() != newDeliveryPeriodAverage;
    }

    private boolean isUpdateReturnMethodDomestic(ProductDelivery existing, ReturnMethod newReturnMethodDomestic) {
        return existing.getReturnMethodDomestic() != newReturnMethodDomestic;
    }

    private boolean isUpdateReturnCourierDomestic(ProductDelivery existing, ShipmentCompanyCode newReturnCourierDomestic) {
        return existing.getReturnCourierDomestic() != newReturnCourierDomestic;
    }

    private boolean isUpdateReturnChargeDomestic(ProductDelivery existing, BigDecimal newReturnChargeDomestic) {
        return !Objects.equals(existing.getReturnChargeDomestic(), Money.wons(newReturnChargeDomestic));
    }

    private boolean isUpdateReturnExchangeAreaDomestic(ProductDelivery existing, String newReturnExchangeAreaDomestic) {
        return !Objects.equals(existing.getReturnExchangeAreaDomestic(), newReturnExchangeAreaDomestic);
    }

}
