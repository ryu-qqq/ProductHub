package com.ryuqq.setof.storage.db.core.site;


import com.ryuqq.setof.enums.core.AdjustmentType;
import com.ryuqq.setof.enums.core.PriceConditionType;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Table(name = "PRICE_POLICY_RULE")
@Entity
public class PricePolicyRuleEntity extends BaseEntity {


    @Column(name = "POLICY_ID", nullable = false)
    private long policyId;

    @Column(name = "CONDITION_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PriceConditionType conditionType;

    @Column(name = "CONDITION_VALUE", length = 255, nullable = false)
    private String conditionValue;

    @Column(name = "ADJUSTMENT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdjustmentType adjustmentType;

    @Column(name = "ADJUSTMENT_VALUE", nullable = false)
    private BigDecimal adjustmentValue;

    protected PricePolicyRuleEntity() {}

    public PricePolicyRuleEntity(long policyId, PriceConditionType conditionType, String conditionValue, AdjustmentType adjustmentType, BigDecimal adjustmentValue) {
        this.policyId = policyId;
        this.conditionType = conditionType;
        this.conditionValue = conditionValue;
        this.adjustmentType = adjustmentType;
        this.adjustmentValue = adjustmentValue;
    }
}
