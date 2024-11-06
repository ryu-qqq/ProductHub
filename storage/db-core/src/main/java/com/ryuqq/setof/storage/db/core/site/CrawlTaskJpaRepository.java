package com.ryuqq.setof.storage.db.core.site;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrawlTaskJpaRepository extends JpaRepository<CrawlTaskEntity, Long> {

    List<CrawlTaskEntity> findByEndpointId(long endpointId);
}
