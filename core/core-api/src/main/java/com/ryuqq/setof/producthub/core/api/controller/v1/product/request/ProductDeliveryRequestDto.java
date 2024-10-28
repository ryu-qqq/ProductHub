package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.core.ReturnMethod;
import com.ryuqq.setof.core.ShipmentCompanyCode;
import com.ryuqq.setof.domain.core.product.command.ProductDeliveryCommand;

import java.math.BigDecimal;

import static com.ryuqq.setof.producthub.core.api.controller.ValidationUtils.*;

public record ProductDeliveryRequestDto(
        String deliveryArea,
        BigDecimal deliveryFee,
        int deliveryPeriodAverage,
        ReturnMethod returnMethodDomestic,
        ShipmentCompanyCode returnCourierDomestic,
        BigDecimal returnChargeDomestic,
        String returnExchangeAreaDomestic
){
    public ProductDeliveryRequestDto {
        validateFields(deliveryArea, deliveryFee, deliveryPeriodAverage, returnMethodDomestic, returnCourierDomestic, returnChargeDomestic, returnExchangeAreaDomestic);
    }

    private void validateFields(String deliveryArea,
                                BigDecimal deliveryFee,
                                int deliveryPeriodAverage,
                                ReturnMethod returnMethodDomestic,
                                ShipmentCompanyCode returnCourierDomestic,
                                BigDecimal returnChargeDomestic,
                                String returnExchangeAreaDomestic
    ) {
        validateString(deliveryArea, 15, "Delivery Area");
        validateBigDecimal(deliveryFee, BigDecimal.valueOf(50000), "Delivery Fee");
        validateInt(deliveryPeriodAverage, 14, "Delivery Period Average");
        validateEnum(returnMethodDomestic,  "Return Method Domestic");
        validateEnum(returnCourierDomestic,  "Return Courier Domestic");
        validateBigDecimal(returnChargeDomestic, BigDecimal.valueOf(50000), "Return Charge Domestic");
        validateString(returnExchangeAreaDomestic, 15, "Return Exchange Area Domestic");
    }

    public ProductDeliveryCommand toProductDelivery(){
        return new ProductDeliveryCommand(deliveryArea, deliveryFee, deliveryPeriodAverage, returnMethodDomestic, returnCourierDomestic, returnChargeDomestic, returnExchangeAreaDomestic);
    }



}
