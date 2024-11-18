package com.ryuqq.setof.storage.db.index.product;

import com.ryuqq.setof.enums.core.ReturnMethod;
import com.ryuqq.setof.enums.core.ShipmentCompanyCode;

import java.math.BigDecimal;

public record ProductDeliveryDocument(String deliveryArea, BigDecimal deliveryFee, int deliveryPeriodAverage,
                                      ReturnMethod returnMethodDomestic, ShipmentCompanyCode returnCourierDomestic,
                                      BigDecimal returnChargeDomestic, String returnExchangeAreaDomestic) {
}
