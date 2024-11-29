package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.external.ExternalPricePolicyRuleCommand;
import com.ryuqq.setof.enums.core.AdjustmentType;
import com.ryuqq.setof.enums.core.PriceConditionType;

import java.math.BigDecimal;

public record ExternalSitePricePolicyRuleRequestDto(
        PriceConditionType conditionType,
        String conditionValue,
        AdjustmentType adjustmentType,
        BigDecimal adjustmentValue,
        int priority,
        boolean activeYn
) {
    public ExternalPricePolicyRuleCommand toExternalSitePricePolicyConditionCommand(){
        return new ExternalPricePolicyRuleCommand(conditionType, conditionValue, adjustmentType, adjustmentValue, priority, activeYn);
    }

}
