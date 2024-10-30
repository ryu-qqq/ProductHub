package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;

@Service
public class CrawlEndPointJpaService implements CrawlEndPointPersistenceService{

    private final CrawlEndPointJpaRepository crawlEndPointJpaRepository;

    public CrawlEndPointJpaService(CrawlEndPointJpaRepository crawlEndPointJpaRepository) {
        this.crawlEndPointJpaRepository = crawlEndPointJpaRepository;
    }

    @Override
    public void insert(CrawlEndpointEntity crawlEndpointEntity) {
        crawlEndPointJpaRepository.save(crawlEndpointEntity);
    }
}
