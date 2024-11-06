package com.ryuqq.setof.domain.core.site.command;

import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.storage.db.core.site.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawlSiteProfileCommandService implements SiteProfileCommandService<CrawlSiteProfileCommand>{

    private final CrawlSettingPersistenceService crawlSettingPersistenceService;
    private final SiteAuthPersistenceService siteAuthPersistenceService;
    private final CrawlMappingPersistenceService crawlMappingPersistenceService;
    private final CrawlEndPointPersistenceService crawlEndPointPersistenceService;
    private final CrawlTaskPersistenceService crawlTaskPersistenceService;

    public CrawlSiteProfileCommandService(CrawlSettingPersistenceService crawlSettingPersistenceService, SiteAuthPersistenceService siteAuthPersistenceService, CrawlMappingPersistenceService crawlMappingPersistenceService, CrawlEndPointPersistenceService crawlEndPointPersistenceService, CrawlTaskPersistenceService crawlTaskPersistenceService) {
        this.crawlSettingPersistenceService = crawlSettingPersistenceService;
        this.siteAuthPersistenceService = siteAuthPersistenceService;
        this.crawlMappingPersistenceService = crawlMappingPersistenceService;
        this.crawlEndPointPersistenceService = crawlEndPointPersistenceService;
        this.crawlTaskPersistenceService = crawlTaskPersistenceService;
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

        long crawlSettingId = crawlSettingPersistenceService.insert(crawlSettingCommand.toCrawlSettingEntity(siteId));
        long authSettingId = siteAuthPersistenceService.insert(crawlAuthSettingCommand.toSiteAuthSettingEntity(siteId));

        long mappingId = crawlMappingPersistenceService.insert(siteId, crawlSettingId, authSettingId);

        crawlEndpointCommands.forEach(c ->{
            long endpointId = crawlEndPointPersistenceService.insert(c.toCrawlEndpointEntity(siteId, mappingId));
            c.crawlTasks().forEach(ct -> {
                CrawlTaskEntity crawlTaskEntity = ct.toCrawlTaskEntity(endpointId);
                crawlTaskPersistenceService.insert(crawlTaskEntity);
            });
        });
    }

    @Override
    public void update(long siteId, long mappingId, CrawlSiteProfileCommand siteProfileCommand) {
        CrawlSettingCommand crawlSettingCommand = siteProfileCommand.crawlSettingCommand();
        CrawlAuthSettingCommand crawlAuthSettingCommand = siteProfileCommand.crawlAuthSettingCommand();
        List<CrawlEndpointCommand> crawlEndpointCommands = siteProfileCommand.crawlEndpointCommands();




    }

}
