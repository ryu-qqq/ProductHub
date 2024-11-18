package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.enums.core.SiteType;

public interface SiteProfileCommandService<T extends SiteProfileCommand> {

    SiteType getSiteType();
    void insert(long siteId, T siteProfileCommand);
    void update(long siteId, long mappingId, T siteProfileCommand);

}
