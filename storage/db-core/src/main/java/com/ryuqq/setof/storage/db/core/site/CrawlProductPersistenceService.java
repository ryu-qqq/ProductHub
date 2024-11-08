package com.ryuqq.setof.storage.db.core.site;

import java.util.List;

public interface CrawlProductPersistenceService {

    void saveAll(List<CrawlProductEntity> crawlProductEntities);
}
