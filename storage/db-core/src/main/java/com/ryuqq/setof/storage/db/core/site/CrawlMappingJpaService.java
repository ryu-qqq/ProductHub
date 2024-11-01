package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;

@Service
public class CrawlMappingJpaService implements CrawlMappingPersistenceService{

    private final CrawlMappingJpaRepository crawlMappingJpaRepository;

    public CrawlMappingJpaService(CrawlMappingJpaRepository crawlMappingJpaRepository) {
        this.crawlMappingJpaRepository = crawlMappingJpaRepository;
    }

    @Override
    public long insert(long crawlSettingId, long authSettingId) {
        return crawlMappingJpaRepository.save(new CrawlMappingEntity(crawlSettingId, authSettingId)).getId();
    }

}
