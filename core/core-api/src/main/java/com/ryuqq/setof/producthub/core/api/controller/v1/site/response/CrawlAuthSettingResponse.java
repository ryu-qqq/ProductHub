package com.ryuqq.setof.producthub.core.api.controller.v1.site.response;

import com.ryuqq.setof.core.AuthType;
import com.ryuqq.setof.domain.core.site.CrawlAuthSetting;

public record CrawlAuthSettingResponse(
        AuthType authType,
        String authEndpoint,
        String authHeaders,
        String authPayload
) {

    public static CrawlAuthSettingResponse of(CrawlAuthSetting crawlAuthSetting) {
        return new CrawlAuthSettingResponse(
                crawlAuthSetting.getAuthType(),
                crawlAuthSetting.getAuthEndpoint(),
                crawlAuthSetting.getAuthHeaders(),
                crawlAuthSetting.getAuthPayload()
        );
    }
}

