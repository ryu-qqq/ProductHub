package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;

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

}
