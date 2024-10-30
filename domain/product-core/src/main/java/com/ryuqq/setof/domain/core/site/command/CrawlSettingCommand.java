package com.ryuqq.setof.domain.core.site.command;

import com.ryuqq.setof.core.CrawlType;
import com.ryuqq.setof.storage.db.core.site.CrawlSettingEntity;

public record CrawlSettingCommand(
        int crawlFrequency,
        CrawlType crawlType

) {
    public CrawlSettingEntity toCrawlSettingEntity(long siteId) {
        return new CrawlSettingEntity(siteId, crawlFrequency, crawlType);
    }

}
