package com.ryuqq.setof.domain.core.site.crawl;

import com.ryuqq.setof.enums.core.CrawlType;
import com.ryuqq.setof.db.core.site.crawl.CrawlSettingEntity;

public record CrawlSettingCommand(
        int crawlFrequency,
        CrawlType crawlType

) {
    public CrawlSettingEntity toCrawlSettingEntity(long siteId) {
        return new CrawlSettingEntity(siteId, crawlFrequency, crawlType);
    }

}
