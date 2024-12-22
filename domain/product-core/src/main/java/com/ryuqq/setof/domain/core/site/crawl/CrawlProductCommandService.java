package com.ryuqq.setof.domain.core.site.crawl;

import com.ryuqq.setof.db.core.site.crawl.CrawlProductCommandhybridService;
import com.ryuqq.setof.db.core.site.crawl.CrawlProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawlProductCommandService {

    private final CrawlProductCommandhybridService crawlProductCommandhybridService;

    public CrawlProductCommandService(CrawlProductCommandhybridService crawlProductCommandhybridService) {
        this.crawlProductCommandhybridService = crawlProductCommandhybridService;
    }

    @Transactional
    public void saveAll(List<CrawlProductCommand> crawlProductCommands){
        List<CrawlProductEntity> crawlProductEntities = crawlProductCommands.stream().map(CrawlProductCommand::toCrawlProductEntity).toList();
        crawlProductCommandhybridService.saveIfNotExists(crawlProductEntities);
    }

}
