package com.ryuqq.setof.storage.db.core.site.external;


import com.ryuqq.setof.enums.core.AdjustmentType;
import com.ryuqq.setof.enums.core.PriceConditionType;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Table(name = "EXTERNAL_PRICE_POLICY_RULE")
@Entity
public class ExternalPricePolicyRuleEntity extends BaseEntity {

    @Column(name = "PRICE_POLICY_ID", nullable = false)
    private long pricePolicyId;

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

    @Column(name = "PRIORITY", nullable = false)
    private int priority;

    @Column(name = "ACTIVE_YN", nullable = false)
    private boolean activeYn;

    protected ExternalPricePolicyRuleEntity() {}

    public ExternalPricePolicyRuleEntity(long pricePolicyId, PriceConditionType conditionType, String conditionValue, AdjustmentType adjustmentType, BigDecimal adjustmentValue, int priority, boolean activeYn) {
        this.pricePolicyId = pricePolicyId;
        this.conditionType = conditionType;
        this.conditionValue = conditionValue;
        this.adjustmentType = adjustmentType;
        this.adjustmentValue = adjustmentValue;
        this.priority = priority;
        this.activeYn = activeYn;
    }


}
