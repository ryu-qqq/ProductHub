package com.ryuqq.setof.api.core.controller.v1.product.response;

import com.ryuqq.setof.enums.core.ReturnMethod;
import com.ryuqq.setof.enums.core.ShipmentCompanyCode;

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
