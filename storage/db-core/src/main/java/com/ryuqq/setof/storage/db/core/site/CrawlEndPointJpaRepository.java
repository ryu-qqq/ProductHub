package com.ryuqq.setof.storage.db.core.site;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrawlEndPointJpaRepository extends JpaRepository<CrawlEndpointEntity, Long> {

    List<CrawlEndpointEntity> findByCrawlMappingId(long crawlMappingId);
}
