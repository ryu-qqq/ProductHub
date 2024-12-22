package com.ryuqq.setof.domain.core.site.crawl;

import com.ryuqq.setof.enums.core.AuthType;
import com.ryuqq.setof.db.core.site.SiteAuthSettingEntity;

public record CrawlAuthSettingCommand(
        AuthType authType,
        String authEndpoint,
        String authHeaders,
        String authPayload
) {

    public SiteAuthSettingEntity toSiteAuthSettingEntity(long siteId){
        return new SiteAuthSettingEntity(siteId, authType, authEndpoint, authHeaders, authPayload);
    }
}
