package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.external.ExternalProductPolicyCommand;
import com.ryuqq.setof.enums.core.Origin;

public record ExternalSiteProductPolicyRequestDto(
        Origin countryCode,
        boolean translated
)
{
    public ExternalProductPolicyCommand toExternalSiteProductPolicyCommand(){
        return new ExternalProductPolicyCommand(countryCode, translated);
    }
}

