package com.ryuqq.setof.domain.core.site;

public record ExternalSiteProfileCommand(
        ExternalSitePricePolicyCommand externalSitePricePolicyCommand
) implements SiteProfileCommand{}
