package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.external.ExternalSiteProfileCommand;

public record ExternalSiteProfileRequestDto(
        String name,
        String description,
        ExternalSiteProductPolicyRequestDto productPolicy,
        ExternalSitePricePolicyRequestDto pricePolicy
) implements SiteProfileRequestDto {

    @Override
    public ExternalSiteProfileCommand toSiteProfileCommand() {
        return new ExternalSiteProfileCommand(
                name,
                description,
                productPolicy.toExternalSiteProductPolicyCommand(),
                pricePolicy.toExternalSitePricePolicyCommand()
        );
    }

}
