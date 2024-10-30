package com.ryuqq.setof.domain.core.site.command;

import com.ryuqq.setof.core.SiteType;

public interface SiteProfileCommandService<T extends SiteProfileCommand> {

    SiteType getSiteType();
    void insert(long siteId, T siteProfileCommand);
}
