package com.ryuqq.setof.storage.db.index.product;

import java.math.BigDecimal;

public class ProductDeliveryDocument {
    private String deliveryArea;
    private BigDecimal deliveryFee;
    private int deliveryPeriodAverage;
    private String returnMethodDomestic;
    private String returnCourierDomestic;
    private BigDecimal returnChargeDomestic;
    private String returnExchangeAreaDomestic;

    public ProductDeliveryDocument(String deliveryArea, BigDecimal deliveryFee, int deliveryPeriodAverage, String returnMethodDomestic, String returnCourierDomestic, BigDecimal returnChargeDomestic, String returnExchangeAreaDomestic) {
        this.deliveryArea = deliveryArea;
        this.deliveryFee = deliveryFee;
        this.deliveryPeriodAverage = deliveryPeriodAverage;
        this.returnMethodDomestic = returnMethodDomestic;
        this.returnCourierDomestic = returnCourierDomestic;
        this.returnChargeDomestic = returnChargeDomestic;
        this.returnExchangeAreaDomestic = returnExchangeAreaDomestic;
    }
}
