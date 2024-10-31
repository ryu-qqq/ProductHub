package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;

@Service
public class SiteJpaService implements SitePersistenceService{

    private final SiteJpaRepository siteJpaRepository;

    public SiteJpaService(SiteJpaRepository siteJpaRepository) {
        this.siteJpaRepository = siteJpaRepository;
    }

    @Override
    public long insert(SiteEntity siteEntity) {
        return siteJpaRepository.save(siteEntity).getId();
    }

}
