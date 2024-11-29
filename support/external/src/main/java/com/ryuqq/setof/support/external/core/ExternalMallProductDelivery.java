package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.ReturnMethod;
import com.ryuqq.setof.enums.core.ShipmentCompanyCode;
import com.ryuqq.setof.support.utils.Money;

public record ExternalMallProductDelivery(
        String deliveryArea,
        Money deliveryFee,
        int deliveryPeriodAverage,
        ReturnMethod returnMethodDomestic,
        ShipmentCompanyCode returnCourierDomestic,
        Money returnChargeDomestic,
        String returnExchangeAreaDomestic
) {
}
