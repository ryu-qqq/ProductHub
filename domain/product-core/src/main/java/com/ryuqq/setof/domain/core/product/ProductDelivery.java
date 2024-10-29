package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.core.ReturnMethod;
import com.ryuqq.setof.core.ShipmentCompanyCode;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductDelivery {

    private long productGroupId;
    private String deliveryArea;
    private BigDecimal deliveryFee;
    private int deliveryPeriodAverage;
    private ReturnMethod returnMethodDomestic;
    private ShipmentCompanyCode returnCourierDomestic;
    private BigDecimal returnChargeDomestic;
    private String returnExchangeAreaDomestic;

    public ProductDelivery(long productGroupId, String deliveryArea, BigDecimal deliveryFee, int deliveryPeriodAverage, ReturnMethod returnMethodDomestic, ShipmentCompanyCode returnCourierDomestic, BigDecimal returnChargeDomestic, String returnExchangeAreaDomestic) {
        this.productGroupId = productGroupId;
        this.deliveryArea = deliveryArea;
        this.deliveryFee = deliveryFee;
        this.deliveryPeriodAverage = deliveryPeriodAverage;
        this.returnMethodDomestic = returnMethodDomestic;
        this.returnCourierDomestic = returnCourierDomestic;
        this.returnChargeDomestic = returnChargeDomestic;
        this.returnExchangeAreaDomestic = returnExchangeAreaDomestic;
    }

    public long getProductGroupId() {
        return productGroupId;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ProductDelivery that = (ProductDelivery) object;
        return productGroupId == that.productGroupId && deliveryPeriodAverage == that.deliveryPeriodAverage && Objects.equals(deliveryArea, that.deliveryArea) && Objects.equals(deliveryFee, that.deliveryFee) && returnMethodDomestic == that.returnMethodDomestic && returnCourierDomestic == that.returnCourierDomestic && Objects.equals(returnChargeDomestic, that.returnChargeDomestic) && Objects.equals(returnExchangeAreaDomestic, that.returnExchangeAreaDomestic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupId, deliveryArea, deliveryFee, deliveryPeriodAverage, returnMethodDomestic, returnCourierDomestic, returnChargeDomestic, returnExchangeAreaDomestic);
    }

    @Override
    public String toString() {
        return "ProductDelivery{" +
                "productGroupId=" + productGroupId +
                ", deliveryArea='" + deliveryArea + '\'' +
                ", deliveryFee=" + deliveryFee +
                ", deliveryPeriodAverage=" + deliveryPeriodAverage +
                ", returnMethodDomestic=" + returnMethodDomestic +
                ", returnCourierDomestic=" + returnCourierDomestic +
                ", returnChargeDomestic=" + returnChargeDomestic +
                ", returnExchangeAreaDomestic='" + returnExchangeAreaDomestic + '\'' +
                '}';
    }
}
