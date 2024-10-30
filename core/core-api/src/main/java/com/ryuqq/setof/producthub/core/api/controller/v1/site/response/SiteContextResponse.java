package com.ryuqq.setof.producthub.core.api.controller.v1.site.response;

import com.ryuqq.setof.core.Origin;
import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.domain.core.site.CrawlSiteProfile;
import com.ryuqq.setof.domain.core.site.SiteContext;

public record SiteContextResponse(
        long siteId,
        String siteName,
        String baseUrl,
        Origin countryCode,
        SiteType siteType,
        SiteProfileResponse siteProfileResponse
) {

    public static SiteContextResponse of(SiteContext siteContext) {
        SiteProfileResponse siteProfileResponse = switch (siteContext.getSiteType()) {
            case CRAWL -> CrawlSiteProfileResponse.of((CrawlSiteProfile) siteContext.getSiteProfile());
            default -> throw new IllegalArgumentException("Unsupported SiteType: " + siteContext.getSiteType());
        };


        return new SiteContextResponse(
                siteContext.getSiteId(),
                siteContext.getSiteName(),
                siteContext.getBaseUrl(),
                siteContext.getCountryCode(),
                siteContext.getSiteType(),
                siteProfileResponse
        );
    }
}
