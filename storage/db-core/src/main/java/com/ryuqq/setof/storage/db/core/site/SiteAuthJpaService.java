package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;

@Service
public class SiteAuthJpaService implements SiteAuthPersistenceService{

    private final SiteAuthSettingJpaRepository siteAuthSettingJpaRepository;

    public SiteAuthJpaService(SiteAuthSettingJpaRepository siteAuthSettingJpaRepository) {
        this.siteAuthSettingJpaRepository = siteAuthSettingJpaRepository;
    }

    @Override
    public long insert(SiteAuthSettingEntity siteAuthSettingEntity) {
        return siteAuthSettingJpaRepository.save(siteAuthSettingEntity).getId();
    }

    @Override
    public void update(long siteAuthSettingId, SiteAuthSettingEntity siteAuthSettingEntity) {
        siteAuthSettingJpaRepository.findById(siteAuthSettingId).ifPresent(entity -> {
            entity.update(siteAuthSettingEntity);
        });
    }

}
