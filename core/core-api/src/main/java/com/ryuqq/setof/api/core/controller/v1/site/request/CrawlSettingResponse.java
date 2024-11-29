package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.crawl.CrawlSetting;
import com.ryuqq.setof.enums.core.CrawlType;

public record CrawlSettingResponse(
        int crawlFrequency,
        CrawlType crawlType
) {
    public static CrawlSettingResponse of(CrawlSetting crawlSetting) {
        return new CrawlSettingResponse(crawlSetting.getCrawlFrequency(), crawlSetting.getCrawlType());
    }

}
