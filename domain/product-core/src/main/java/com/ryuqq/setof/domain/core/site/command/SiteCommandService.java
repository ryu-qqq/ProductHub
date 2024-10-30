package com.ryuqq.setof.domain.core.site.command;

import com.ryuqq.setof.storage.db.core.site.SiteEntity;
import com.ryuqq.setof.storage.db.core.site.SitePersistenceService;
import org.springframework.stereotype.Service;

@Service
public class SiteCommandService {

    private final SitePersistenceService sitePersistenceService;

    public SiteCommandService(SitePersistenceService sitePersistenceService) {
        this.sitePersistenceService = sitePersistenceService;
    }

    public long insert(SiteEntity siteEntity) {
        return sitePersistenceService.insert(siteEntity);
    }

}
