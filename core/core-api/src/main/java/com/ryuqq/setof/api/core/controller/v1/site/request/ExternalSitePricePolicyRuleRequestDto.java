package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.ExternalSitePricePolicyRuleCommand;
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
    public ExternalSitePricePolicyRuleCommand toExternalSitePricePolicyConditionCommand(){
        return new ExternalSitePricePolicyRuleCommand(conditionType, conditionValue, adjustmentType, adjustmentValue, priority, activeYn);
    }

}
