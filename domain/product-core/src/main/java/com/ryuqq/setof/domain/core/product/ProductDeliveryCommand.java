package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.core.ReturnMethod;
import com.ryuqq.setof.core.ShipmentCompanyCode;
import com.ryuqq.setof.storage.db.core.product.delivery.ProductDeliveryEntity;

import java.math.BigDecimal;

public record ProductDeliveryCommand(
        String deliveryArea,
        BigDecimal deliveryFee,
        int deliveryPeriodAverage,
        ReturnMethod returnMethodDomestic,
        ShipmentCompanyCode returnCourierDomestic,
        BigDecimal returnChargeDomestic,
        String returnExchangeAreaDomestic
) {
    public ProductDeliveryEntity toEntity(long productGroupId){
        return new ProductDeliveryEntity(productGroupId, deliveryArea, deliveryFee, deliveryPeriodAverage,
                returnMethodDomestic, returnCourierDomestic, returnChargeDomestic, returnExchangeAreaDomestic
                );
    }
}
