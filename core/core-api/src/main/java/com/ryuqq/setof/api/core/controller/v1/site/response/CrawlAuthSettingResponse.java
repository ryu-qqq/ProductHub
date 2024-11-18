package com.ryuqq.setof.api.core.controller.v1.site.response;

import com.ryuqq.setof.domain.core.site.CrawlAuthSetting;
import com.ryuqq.setof.enums.core.AuthType;

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

