package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.site.dto.SiteContextDto;
import com.ryuqq.setof.storage.db.core.site.dto.SiteFilterStorageDto;

import java.util.List;
import java.util.Optional;

public interface SiteQueryRepository {
    boolean fetchSiteExists(long siteId);
    boolean fetchSiteExists(String name, String baseUrl);
    List<SiteContextDto> fetchSites(SiteFilterStorageDto siteFilterStorageDto);
    long fetchSiteCount(SiteFilterStorageDto siteFilterStorageDto);
    Optional<SiteContextDto> fetchSiteContext(long siteId);

}
