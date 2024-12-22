package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.site.dto.SiteContextDto;
import com.ryuqq.setof.storage.db.core.site.dto.SiteFilterStorageDto;

import java.util.List;
import java.util.Optional;

public interface SiteQueryRepository {

    boolean existById(long siteId);
    boolean existByNameAndUrl(String name, String baseUrl);
    List<SiteContextDto> fetchByFilter(SiteFilterStorageDto siteFilterStorageDto);
    long countByFilter(SiteFilterStorageDto siteFilterStorageDto);
    Optional<SiteContextDto> fetchSiteContext(long siteId);

}
