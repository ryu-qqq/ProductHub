package com.ryuqq.setof.api.core.controller.v1.site.response;

import com.ryuqq.setof.api.core.controller.v1.site.request.CrawlSettingResponse;
import com.ryuqq.setof.domain.core.site.crawl.CrawlSiteProfile;

import java.util.List;
import java.util.Map;

public record CrawlSiteProfileResponse(
        long mappingId,
        CrawlSettingResponse crawlSetting,
        CrawlAuthSettingResponse crawlAuthSetting,
        List<CrawlEndpointResponse> crawlEndpoints,
        Map<String, String> headers

) implements SiteProfileResponse {

    public static CrawlSiteProfileResponse of(CrawlSiteProfile crawlSiteProfile) {
        return new CrawlSiteProfileResponse(
                crawlSiteProfile.getMappingId(),
                CrawlSettingResponse.of(crawlSiteProfile.getCrawlSetting()),
                CrawlAuthSettingResponse.of(crawlSiteProfile.getCrawlAuthSetting()),
                crawlSiteProfile.getCrawlEndpoints().stream().map(CrawlEndpointResponse::of).toList(),
                crawlSiteProfile.getHeaders()
        );
    }

}
