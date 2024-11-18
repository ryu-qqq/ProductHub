package com.ryuqq.setof.producthub.core.api.controller.v1.site.response;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.SiteType;
import com.ryuqq.setof.domain.core.site.CrawlSiteProfile;
import com.ryuqq.setof.domain.core.site.SiteContext;

import java.util.List;

public record SiteContextResponse(
        long siteId,
        String siteName,
        String baseUrl,
        Origin countryCode,
        SiteType siteType,
        List<SiteProfileResponse> siteProfiles
) {

    public static SiteContextResponse of(SiteContext siteContext) {
        List<SiteProfileResponse> siteProfileResponses;

        switch (siteContext.getSiteType()) {
            case CRAWL -> {
                siteProfileResponses = siteContext.getSiteProfiles().stream()
                        .map(profile -> (SiteProfileResponse) CrawlSiteProfileResponse.of((CrawlSiteProfile) profile))
                        .toList();
            }
            default -> throw new IllegalArgumentException("Unsupported SiteType: " + siteContext.getSiteType());
        }

        return new SiteContextResponse(
                siteContext.getSiteId(),
                siteContext.getSiteName(),
                siteContext.getBaseUrl(),
                siteContext.getCountryCode(),
                siteContext.getSiteType(),
                siteProfileResponses
        );
    }
}
