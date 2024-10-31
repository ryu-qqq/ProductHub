package com.ryuqq.setof.domain.core.site.command;

import com.ryuqq.setof.core.Origin;
import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.storage.db.core.site.SiteEntity;

public record SiteCommand(
        String name,
        String baseUrl,
        Origin countryCode,
        SiteType siteType,
        SiteProfileCommand siteProfileCommand
) {

    public SiteEntity toSiteEntity(){
        return new SiteEntity(name, baseUrl, countryCode, siteType);
    }

}
