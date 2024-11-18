package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.SiteType;
import com.ryuqq.setof.storage.db.core.site.SiteEntity;

public record SiteCommand(
        String name,
        String baseUrl,
        Origin countryCode,
        SiteType siteType
) {

    public SiteEntity toSiteEntity(){
        return new SiteEntity(name, baseUrl, countryCode, siteType);
    }

}
