package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.core.ReturnMethod;
import com.ryuqq.setof.core.ShipmentCompanyCode;

import java.math.BigDecimal;

public class ProductDelivery {

    private String deliveryArea;
    private BigDecimal deliveryFee;
    private int deliveryPeriodAverage;
    private ReturnMethod returnMethodDomestic;
    private ShipmentCompanyCode returnCourierDomestic;
    private BigDecimal returnChargeDomestic;
    private String returnExchangeAreaDomestic;

}
