package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;

@Service
public class CrawlSettingJpaService implements CrawlSettingPersistenceService{

    private final CrawlSettingJpaRepository crawlSettingJpaRepository;

    public CrawlSettingJpaService(CrawlSettingJpaRepository crawlSettingJpaRepository) {
        this.crawlSettingJpaRepository = crawlSettingJpaRepository;
    }

    @Override
    public void insert(CrawlSettingEntity crawlSettingEntity) {
        crawlSettingJpaRepository.save(crawlSettingEntity);
    }
}
