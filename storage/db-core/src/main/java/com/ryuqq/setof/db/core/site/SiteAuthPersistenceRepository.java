package com.ryuqq.setof.storage.db.core.site;

public interface SiteAuthPersistenceRepository {
    long insert(SiteAuthSettingEntity siteAuthSettingEntity);
    void update(long siteAuthSettingId, SiteAuthSettingEntity siteAuthSettingEntity);
}
