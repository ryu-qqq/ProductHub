package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;

@Service
public class CrawlSettingJpaService implements CrawlSettingPersistenceService{

    private final CrawlSettingJpaRepository crawlSettingJpaRepository;

    public CrawlSettingJpaService(CrawlSettingJpaRepository crawlSettingJpaRepository) {
        this.crawlSettingJpaRepository = crawlSettingJpaRepository;
    }

    @Override
    public long insert(CrawlSettingEntity crawlSettingEntity) {
        return crawlSettingJpaRepository.save(crawlSettingEntity).getId();
    }

}
