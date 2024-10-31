package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.site.dto.SiteContextDto;

import java.util.Optional;

public interface SiteQueryRepository {

    Optional<SiteContextDto> fetchSiteContext(long siteId);

}
