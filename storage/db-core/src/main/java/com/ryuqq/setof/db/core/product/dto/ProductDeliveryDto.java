package com.ryuqq.setof.storage.db.core.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.ReturnMethod;
import com.ryuqq.setof.enums.core.ShipmentCompanyCode;

import java.math.BigDecimal;

public class ProductDeliveryDto {

    private String deliveryArea;
    private BigDecimal deliveryFee;
    private int deliveryPeriodAverage;
    private ReturnMethod returnMethodDomestic;
    private ShipmentCompanyCode returnCourierDomestic;
    private BigDecimal returnChargeDomestic;
    private String returnExchangeAreaDomestic;

    protected ProductDeliveryDto() {}

    @QueryProjection
    public ProductDeliveryDto(String deliveryArea, BigDecimal deliveryFee, int deliveryPeriodAverage, ReturnMethod returnMethodDomestic, ShipmentCompanyCode returnCourierDomestic, BigDecimal returnChargeDomestic, String returnExchangeAreaDomestic) {
        this.deliveryArea = deliveryArea;
        this.deliveryFee = deliveryFee;
        this.deliveryPeriodAverage = deliveryPeriodAverage;
        this.returnMethodDomestic = returnMethodDomestic;
        this.returnCourierDomestic = returnCourierDomestic;
        this.returnChargeDomestic = returnChargeDomestic;
        this.returnExchangeAreaDomestic = returnExchangeAreaDomestic;
    }

    public String getDeliveryArea() {
        return deliveryArea;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public int getDeliveryPeriodAverage() {
        return deliveryPeriodAverage;
    }

    public ReturnMethod getReturnMethodDomestic() {
        return returnMethodDomestic;
    }

    public ShipmentCompanyCode getReturnCourierDomestic() {
        return returnCourierDomestic;
    }

    public BigDecimal getReturnChargeDomestic() {
        return returnChargeDomestic;
    }

    public String getReturnExchangeAreaDomestic() {
        return returnExchangeAreaDomestic;
    }
}
