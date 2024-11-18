package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.ReturnMethod;
import com.ryuqq.setof.enums.core.ShipmentCompanyCode;
import com.ryuqq.setof.domain.core.generic.Money;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductDeliveryUpdater {

    private final ProductDelivery productDelivery;

    public ProductDeliveryUpdater(ProductDelivery productDelivery) {
        this.productDelivery = productDelivery;
    }

    public boolean isUpdateDeliveryArea(String newDeliveryArea) {
        return !Objects.equals(productDelivery.getDeliveryArea(), newDeliveryArea);
    }

    public boolean isUpdateDeliveryFee(BigDecimal newDeliveryFee) {
        return Objects.equals(productDelivery.getDeliveryFee(), Money.wons(newDeliveryFee));
    }

    public boolean isUpdateDeliveryPeriodAverage(int newDeliveryPeriodAverage) {
        return productDelivery.getDeliveryPeriodAverage() != newDeliveryPeriodAverage;
    }

    public boolean isUpdateReturnMethodDomestic(ReturnMethod newReturnMethodDomestic) {
        return productDelivery.getReturnMethodDomestic() != newReturnMethodDomestic;
    }

    public boolean isUpdateReturnCourierDomestic(ShipmentCompanyCode newReturnCourierDomestic) {
        return productDelivery.getReturnCourierDomestic() != newReturnCourierDomestic;
    }

    public boolean isUpdateReturnChargeDomestic(BigDecimal newReturnChargeDomestic) {
        return Objects.equals(productDelivery.getReturnChargeDomestic(), Money.wons(newReturnChargeDomestic));
    }

    public boolean isUpdateReturnExchangeAreaDomestic(String newReturnExchangeAreaDomestic) {
        return !Objects.equals(productDelivery.getReturnExchangeAreaDomestic(), newReturnExchangeAreaDomestic);
    }

    public boolean hasUpdates(ProductDeliveryCommand command) {
        return isUpdateDeliveryArea(command.deliveryArea()) ||
                isUpdateDeliveryFee(command.deliveryFee()) ||
                isUpdateDeliveryPeriodAverage(command.deliveryPeriodAverage()) ||
                isUpdateReturnMethodDomestic(command.returnMethodDomestic()) ||
                isUpdateReturnCourierDomestic(command.returnCourierDomestic()) ||
                isUpdateReturnChargeDomestic(command.returnChargeDomestic()) ||
                isUpdateReturnExchangeAreaDomestic(command.returnExchangeAreaDomestic());
    }
}
