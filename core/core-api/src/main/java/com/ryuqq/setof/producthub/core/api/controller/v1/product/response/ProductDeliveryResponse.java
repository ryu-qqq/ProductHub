package com.ryuqq.setof.producthub.core.api.controller.v1.product.response;

import com.ryuqq.setof.core.ReturnMethod;
import com.ryuqq.setof.core.ShipmentCompanyCode;

import java.math.BigDecimal;

public record ProductDeliveryResponse(
        String deliveryArea,
        BigDecimal deliveryFee,
        int deliveryPeriodAverage,
        ReturnMethod returnMethodDomestic,
        ShipmentCompanyCode returnCourierDomestic,
        BigDecimal returnChargeDomestic,
        String returnExchangeAreaDomestic
) {}
