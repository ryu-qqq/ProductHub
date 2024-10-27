package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.core.ReturnMethod;
import com.ryuqq.setof.core.ShipmentCompanyCode;

import java.math.BigDecimal;

public record ProductDelivery(
        String deliveryArea,
        BigDecimal deliveryFee,
        int deliveryPeriodAverage,
        ReturnMethod returnMethodDomestic,
        ShipmentCompanyCode returnCourierDomestic,
        BigDecimal returnChargeDomestic,
        String returnExchangeAreaDomestic
) {}
