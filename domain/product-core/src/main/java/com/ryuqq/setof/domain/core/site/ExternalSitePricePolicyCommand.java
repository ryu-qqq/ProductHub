package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.storage.db.core.site.ExternalPricePolicyEntity;

import java.util.List;

public record ExternalSitePricePolicyCommand(
        String name,
        String description,
        Origin currency,
        int priority,
        List<ExternalSitePricePolicyRuleCommand> externalSitePricePolicyRuleCommands
) {
    public ExternalPricePolicyEntity toEntity(long siteId){
        return new ExternalPricePolicyEntity(siteId, name, description, currency, priority);
    }

}
