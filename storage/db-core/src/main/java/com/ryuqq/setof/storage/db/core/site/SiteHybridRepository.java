package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;

@Service
public class SiteHybridRepository implements SitePersistenceRepository {

    private final SiteJpaRepository siteJpaRepository;

    public SiteHybridRepository(SiteJpaRepository siteJpaRepository) {
        this.siteJpaRepository = siteJpaRepository;
    }

    @Override
    public long insert(SiteEntity siteEntity) {
        return siteJpaRepository.save(siteEntity).getId();
    }

}
