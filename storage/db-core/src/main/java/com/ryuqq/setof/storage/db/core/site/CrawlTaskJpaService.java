package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawlTaskJpaService implements CrawlTaskPersistenceService{

    private final CrawlTaskJpaRepository crawlTaskJpaRepository;

    public CrawlTaskJpaService(CrawlTaskJpaRepository crawlTaskJpaRepository) {
        this.crawlTaskJpaRepository = crawlTaskJpaRepository;
    }

    @Override
    public void insert(CrawlTaskEntity crawlTaskEntity){
        crawlTaskJpaRepository.save(crawlTaskEntity);
    }

    @Override
    public void delete(long endpointId) {
        List<CrawlTaskEntity> crawlTaskEntities = crawlTaskJpaRepository.findByEndpointId(endpointId);
        crawlTaskEntities.forEach(CrawlTaskEntity::delete);
    }

}
