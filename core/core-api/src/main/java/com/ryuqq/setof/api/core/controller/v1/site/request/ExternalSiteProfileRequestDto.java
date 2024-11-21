package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.ExternalSiteProfileCommand;

public record ExternalSiteProfileRequestDto(
        String name,
        String description,
        ExternalSiteProductPolicyRequestDto externalSiteProductPolicyRequest,
        ExternalSitePricePolicyRequestDto externalSitePricePolicyRequest
) implements SiteProfileRequestDto {

    @Override
    public ExternalSiteProfileCommand toSiteProfileCommand() {
        return new ExternalSiteProfileCommand(externalSitePricePolicyRequest.toExternalSitePricePolicyCommand());
    }

}
