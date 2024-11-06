package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawlEndPointJpaService implements CrawlEndPointPersistenceService{

    private final CrawlEndPointJpaRepository crawlEndPointJpaRepository;

    public CrawlEndPointJpaService(CrawlEndPointJpaRepository crawlEndPointJpaRepository) {
        this.crawlEndPointJpaRepository = crawlEndPointJpaRepository;
    }

    @Override
    public long insert(CrawlEndpointEntity crawlEndpointEntity) {
        return crawlEndPointJpaRepository.save(crawlEndpointEntity).getId();
    }

    @Override
    public void delete(long crawlMappingId) {
        List<CrawlEndpointEntity> crawlEndpointEntities = crawlEndPointJpaRepository.findByCrawlMappingId(crawlMappingId);
        crawlEndpointEntities.forEach(CrawlEndpointEntity::delete);
    }

}
