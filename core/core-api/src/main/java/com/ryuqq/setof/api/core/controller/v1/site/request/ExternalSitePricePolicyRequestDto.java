package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.ExternalSitePricePolicyCommand;
import com.ryuqq.setof.domain.core.site.ExternalSitePricePolicyRuleCommand;
import com.ryuqq.setof.enums.core.Origin;

import java.util.List;

public record ExternalSitePricePolicyRequestDto(
        String name,
        String description,
        Origin currency,
        int priority,
        List<ExternalSitePricePolicyRuleRequestDto> externalSitePricePolicyConditionRequests
)
{
    public ExternalSitePricePolicyCommand toExternalSitePricePolicyCommand() {
        List<ExternalSitePricePolicyRuleCommand> externalSitePricePolicyRuleCommands = externalSitePricePolicyConditionRequests.stream()
                .map(ExternalSitePricePolicyRuleRequestDto::toExternalSitePricePolicyConditionCommand)
                .toList();
        return new ExternalSitePricePolicyCommand(name, description, currency, priority, externalSitePricePolicyRuleCommands);
    }

}
