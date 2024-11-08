package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Component;

@Component
public class CrawlProductCacheFacade {

    private final CrawlProductPersistenceService crawlProductPersistenceService;

    public CrawlProductCacheFacade(CrawlProductPersistenceService crawlProductPersistenceService) {
        this.crawlProductPersistenceService = crawlProductPersistenceService;
    }




}
