package com.ryuqq.setof.producthub.core.api.controller.v1.site.request;

import com.ryuqq.setof.core.CrawlType;
import com.ryuqq.setof.domain.core.site.CrawlSetting;

public record CrawlSettingResponse(
        int crawlFrequency,
        CrawlType crawlType
) {
    public static CrawlSettingResponse of(CrawlSetting crawlSetting) {
        return new CrawlSettingResponse(crawlSetting.getCrawlFrequency(), crawlSetting.getCrawlType());
    }

}
