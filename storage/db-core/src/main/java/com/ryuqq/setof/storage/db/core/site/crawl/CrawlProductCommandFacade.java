package com.ryuqq.setof.storage.db.core.site.crawl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrawlProductCommandFacade {

    private final CrawlProductCacheService crawlProductCacheService;
    private final CrawlProductPersistenceService crawlProductPersistenceService;

    public CrawlProductCommandFacade(CrawlProductCacheService crawlProductCacheService, CrawlProductPersistenceService crawlProductPersistenceService) {
        this.crawlProductCacheService = crawlProductCacheService;
        this.crawlProductPersistenceService = crawlProductPersistenceService;
    }

    @Transactional
    public void saveIfNotExists(List<CrawlProductEntity> crawlProductEntities) {
        List<CrawlProductEntity> entitiesToSave = new ArrayList<>();

        for (CrawlProductEntity entity : crawlProductEntities) {
            String cacheKey = generateCacheKey(entity.getSiteId(), entity.getSiteProductId());

            if (!crawlProductCacheService.productExists(cacheKey)) {
                crawlProductCacheService.saveProduct(cacheKey, entity.getProductName());
                entitiesToSave.add(entity);
            }
        }

        if (!entitiesToSave.isEmpty()) {
            crawlProductPersistenceService.batchInsertCrawlProducts(entitiesToSave);
        }
    }

    private String generateCacheKey(long siteId, String siteProductId) {
        return siteId + ":" + siteProductId;
    }


}
