package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.external.ExternalPricePolicyCommand;
import com.ryuqq.setof.domain.core.site.external.ExternalPricePolicyRuleCommand;
import com.ryuqq.setof.enums.core.Origin;

import java.util.List;

public record ExternalSitePricePolicyRequestDto(
        String name,
        String description,
        Origin currency,
        int priority,
        List<ExternalSitePricePolicyRuleRequestDto> pricePolicyConditions
)
    {
        public ExternalPricePolicyCommand toExternalSitePricePolicyCommand() {
            List<ExternalPricePolicyRuleCommand> externalPricePolicyRuleCommands = pricePolicyConditions.stream()
                    .map(ExternalSitePricePolicyRuleRequestDto::toExternalSitePricePolicyConditionCommand)
                    .toList();
            return new ExternalPricePolicyCommand(name, description, currency, priority, externalPricePolicyRuleCommands);
    }
}
