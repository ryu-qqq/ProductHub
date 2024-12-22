package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.AdjustmentType;
import com.ryuqq.setof.enums.core.PriceConditionType;
import com.ryuqq.setof.db.core.site.external.ExternalPricePolicyRuleEntity;

import java.math.BigDecimal;

public record ExternalPricePolicyRuleCommand(
        PriceConditionType conditionType,
        String conditionValue,
        AdjustmentType adjustmentType,
        BigDecimal adjustmentValue,
        int priority,
        boolean activeYn
){

    public ExternalPricePolicyRuleEntity toEntity(long policyId){
        return new ExternalPricePolicyRuleEntity(
                policyId,
                conditionType,
                conditionValue,
                adjustmentType,
                adjustmentValue,
                priority,
                activeYn
        );
    }

}
