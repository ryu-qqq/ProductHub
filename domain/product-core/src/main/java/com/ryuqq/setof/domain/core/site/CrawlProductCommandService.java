package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.storage.db.core.site.CrawlProductCommandFacade;
import com.ryuqq.setof.storage.db.core.site.CrawlProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawlProductCommandService {

    private final CrawlProductCommandFacade crawlProductCommandFacade;

    public CrawlProductCommandService(CrawlProductCommandFacade crawlProductCommandFacade) {
        this.crawlProductCommandFacade = crawlProductCommandFacade;
    }

    public void inserts(List<CrawlProductCommand> crawlProductCommands){
        List<CrawlProductEntity> crawlProductEntities = crawlProductCommands.stream().map(CrawlProductCommand::toCrawlProductEntity).toList();
        crawlProductCommandFacade.saveIfNotExists(crawlProductEntities);
    }

}
