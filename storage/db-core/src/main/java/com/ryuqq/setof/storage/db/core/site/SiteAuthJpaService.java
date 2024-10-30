package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;

@Service
public class SiteAuthJpaService implements SiteAuthPersistenceService{

    private final SiteAuthSettingJpaRepository siteAuthSettingJpaRepository;

    public SiteAuthJpaService(SiteAuthSettingJpaRepository siteAuthSettingJpaRepository) {
        this.siteAuthSettingJpaRepository = siteAuthSettingJpaRepository;
    }

    @Override
    public void insert(SiteAuthSettingEntity siteAuthSettingEntity) {
        siteAuthSettingJpaRepository.save(siteAuthSettingEntity);
    }

}
