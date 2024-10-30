package com.ryuqq.setof.domain.core.site.command;

import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.storage.db.core.site.CrawlEndPointPersistenceService;
import com.ryuqq.setof.storage.db.core.site.CrawlSettingPersistenceService;
import com.ryuqq.setof.storage.db.core.site.SiteAuthPersistenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawlSiteProfileCommandService implements SiteProfileCommandService<CrawlSiteProfileCommand>{

    private final CrawlSettingPersistenceService crawlSettingPersistenceService;
    private final SiteAuthPersistenceService siteAuthPersistenceService;
    private final CrawlEndPointPersistenceService crawlEndPointPersistenceService;

    public CrawlSiteProfileCommandService(CrawlSettingPersistenceService crawlSettingPersistenceService, SiteAuthPersistenceService siteAuthPersistenceService, CrawlEndPointPersistenceService crawlEndPointPersistenceService) {
        this.crawlSettingPersistenceService = crawlSettingPersistenceService;
        this.siteAuthPersistenceService = siteAuthPersistenceService;
        this.crawlEndPointPersistenceService = crawlEndPointPersistenceService;
    }

    @Override
    public SiteType getSiteType() {
        return SiteType.CRAWL;
    }

    @Override
    public void insert(long siteId, CrawlSiteProfileCommand siteProfileCommand) {
        CrawlSettingCommand crawlSettingCommand = siteProfileCommand.crawlSettingCommand();
        CrawlAuthSettingCommand crawlAuthSettingCommand = siteProfileCommand.crawlAuthSettingCommand();
        List<CrawlEndpointCommand> crawlEndpointCommands = siteProfileCommand.crawlEndpointCommands();

        crawlSettingPersistenceService.insert(crawlSettingCommand.toCrawlSettingEntity(siteId));
        siteAuthPersistenceService.insert(crawlAuthSettingCommand.toSiteAuthSettingEntity(siteId));
        crawlEndpointCommands.forEach(c ->{
            crawlEndPointPersistenceService.insert(c.toCrawlEndpointEntity(siteId));
        });
    }
}
