package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.storage.db.core.site.external.ExternalPricePolicyEntity;

import java.util.List;

public record ExternalSitePricePolicyCommand(
        String name,
        String description,
        Origin currency,
        int priority,
        List<ExternalSitePricePolicyRuleCommand> externalSitePricePolicyRuleCommands
) {
    public ExternalPricePolicyEntity toEntity(long policyId){
        return new ExternalPricePolicyEntity(policyId, name, description, currency, priority);
    }

}
