package com.ryuqq.setof.producthub.core.api.controller.v1.site.response;

import com.ryuqq.setof.domain.core.site.CrawlSiteProfile;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.request.CrawlSettingResponse;

import java.util.List;

public record CrawlSiteProfileResponse(
        CrawlSettingResponse crawlSettingResponse,
        CrawlAuthSettingResponse crawlAuthSetting,
        List<CrawlEndpointResponse> crawlEndpoints
) implements SiteProfileResponse {

    public static CrawlSiteProfileResponse of(CrawlSiteProfile crawlSiteProfile) {
        return new CrawlSiteProfileResponse(
                CrawlSettingResponse.of(crawlSiteProfile.getCrawlSetting()),
                CrawlAuthSettingResponse.of(crawlSiteProfile.getCrawlAuthSetting()),
                crawlSiteProfile.getCrawlEndpoints().stream().map(CrawlEndpointResponse::of).toList()
        );
    }
}
