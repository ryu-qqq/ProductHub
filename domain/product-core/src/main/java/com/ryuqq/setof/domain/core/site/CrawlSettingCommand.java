package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.enums.core.CrawlType;
import com.ryuqq.setof.storage.db.core.site.CrawlSettingEntity;

public record CrawlSettingCommand(
        int crawlFrequency,
        CrawlType crawlType

) {
    public CrawlSettingEntity toCrawlSettingEntity(long siteId) {
        return new CrawlSettingEntity(siteId, crawlFrequency, crawlType);
    }

}
