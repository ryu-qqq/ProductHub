package com.ryuqq.setof.producthub.core.api.controller.v1.site.response;

import com.ryuqq.setof.domain.core.site.CrawlSiteProfile;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.request.CrawlSettingResponse;

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
