package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.enums.core.AdjustmentType;
import com.ryuqq.setof.enums.core.PriceConditionType;
import com.ryuqq.setof.storage.db.core.site.ExternalPricePolicyRuleEntity;

import java.math.BigDecimal;

public record ExternalSitePricePolicyRuleCommand(
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
