package com.ryuqq.setof.api.core.controller.v1.product.request;

import com.ryuqq.setof.domain.core.product.ProductDeliveryCommand;
import com.ryuqq.setof.enums.core.ReturnMethod;
import com.ryuqq.setof.enums.core.ShipmentCompanyCode;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductDeliveryRequestDto(

        @NotBlank(message = "Delivery Area cannot be blank.")
        @Size(max = 15, message = "Delivery Area must be 15 characters or less.")
        String deliveryArea,

        @NotNull(message = "Delivery Fee cannot be null.")
        @DecimalMax(value = "50000", inclusive = true, message = "Delivery Fee must be less than or equal to 50,000.")
        BigDecimal deliveryFee,

        @NotNull(message = "Delivery Period Average cannot be null.")
        @Max(value = 14, message = "Delivery Period Average must be 14 days or less.")
        int deliveryPeriodAverage,

        @NotNull(message = "Return Method Domestic cannot be null.")
        ReturnMethod returnMethodDomestic,

        @NotNull(message = "Return Courier Domestic cannot be null.")
        ShipmentCompanyCode returnCourierDomestic,

        @NotNull(message = "Return Charge Domestic cannot be null.")
        @DecimalMax(value = "50000", inclusive = true, message = "Return Charge Domestic must be less than or equal to 50,000.")
        BigDecimal returnChargeDomestic,

        @NotBlank(message = "Return Exchange Area Domestic cannot be blank.")
        @Size(max = 15, message = "Return Exchange Area Domestic must be 15 characters or less.")
        String returnExchangeAreaDomestic
){

    public ProductDeliveryCommand toProductDelivery(){
        return new ProductDeliveryCommand(deliveryArea, deliveryFee, deliveryPeriodAverage, returnMethodDomestic, returnCourierDomestic, returnChargeDomestic, returnExchangeAreaDomestic);
    }



}
