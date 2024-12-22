package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.db.core.site.SiteEntity;
import com.ryuqq.setof.db.core.site.SitePersistenceRepository;
import org.springframework.stereotype.Service;

@Service
public class SiteCommandService {

    private final SitePersistenceRepository sitePersistenceRepository;

    public SiteCommandService(SitePersistenceRepository sitePersistenceRepository) {
        this.sitePersistenceRepository = sitePersistenceRepository;
    }

    public long insert(SiteEntity siteEntity) {
        return sitePersistenceRepository.insert(siteEntity);
    }

}
