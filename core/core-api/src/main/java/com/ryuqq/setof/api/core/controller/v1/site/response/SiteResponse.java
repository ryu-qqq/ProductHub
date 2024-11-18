package com.ryuqq.setof.api.core.controller.v1.site.response;

import com.ryuqq.setof.domain.core.site.Site;
import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.SiteType;

public record SiteResponse(
        long siteId,
        String siteName,
        String baseUrl,
        Origin countryCode,
        SiteType siteType
) {

    public static SiteResponse of(Site site){
        return new SiteResponse(
                site.getSiteId(),
                site.getSiteName(),
                site.getBaseUrl(),
                site.getCountryCode(),
                site.getSiteType()
        );
    }
}
