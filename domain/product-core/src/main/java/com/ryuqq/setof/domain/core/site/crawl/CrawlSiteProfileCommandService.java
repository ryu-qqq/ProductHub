package com.ryuqq.setof.domain.core.site.crawl;

import com.ryuqq.setof.domain.core.site.SiteProfileCommandService;
import com.ryuqq.setof.enums.core.SiteType;
import com.ryuqq.setof.storage.db.core.site.SiteAuthPersistenceRepository;
import com.ryuqq.setof.storage.db.core.site.crawl.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawlSiteProfileCommandService implements SiteProfileCommandService<CrawlSiteProfileCommand> {

    private final CrawlSettingPersistenceService crawlSettingPersistenceService;
    private final SiteAuthPersistenceRepository siteAuthPersistenceRepository;
    private final CrawlMappingPersistenceService crawlMappingPersistenceService;
    private final CrawlEndPointPersistenceService crawlEndPointPersistenceService;
    private final CrawlTaskPersistenceService crawlTaskPersistenceService;
    private final CrawlSiteProfileFinder crawlSiteProfileFinder;

    public CrawlSiteProfileCommandService(CrawlSettingPersistenceService crawlSettingPersistenceService, SiteAuthPersistenceRepository siteAuthPersistenceRepository, CrawlMappingPersistenceService crawlMappingPersistenceService, CrawlEndPointPersistenceService crawlEndPointPersistenceService, CrawlTaskPersistenceService crawlTaskPersistenceService, CrawlSiteProfileFinder crawlSiteProfileFinder) {
        this.crawlSettingPersistenceService = crawlSettingPersistenceService;
        this.siteAuthPersistenceRepository = siteAuthPersistenceRepository;
        this.crawlMappingPersistenceService = crawlMappingPersistenceService;
        this.crawlEndPointPersistenceService = crawlEndPointPersistenceService;
        this.crawlTaskPersistenceService = crawlTaskPersistenceService;
        this.crawlSiteProfileFinder = crawlSiteProfileFinder;
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
        long authSettingId = siteAuthPersistenceRepository.insert(crawlAuthSettingCommand.toSiteAuthSettingEntity(siteId));

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
        CrawlSiteProfile crawlSiteProfile = crawlSiteProfileFinder.fetchBySiteId(siteId, mappingId);

        CrawlAuthSetting crawlAuthSetting = crawlSiteProfile.getCrawlAuthSetting();
        CrawlAuthSettingCommand crawlAuthSettingCommand = siteProfileCommand.crawlAuthSettingCommand();

        if (crawlAuthSetting.updateIfChanged(crawlAuthSettingCommand)) {
            siteAuthPersistenceRepository.update(crawlAuthSetting.getAuthSettingId(), crawlAuthSettingCommand.toSiteAuthSettingEntity(siteId));
        }

        CrawlSetting crawlSetting = crawlSiteProfile.getCrawlSetting();
        CrawlSettingCommand crawlSettingCommand = siteProfileCommand.crawlSettingCommand();

        if (crawlSetting.updateIfChanged(crawlSettingCommand)) {
            crawlSettingPersistenceService.update(crawlSiteProfile.getCrawlSettingId(), crawlSettingCommand.toCrawlSettingEntity(siteId));
        }

        //Todo:: 현재 크롤링 엔드포인트를 삭제하고 새로 등록해도 큰 문제가 없을듯 하다. 나중에 고도화 작업을 해야햔다.
        List<CrawlEndpoint> crawlEndpoints = crawlSiteProfile.getCrawlEndpoints();
        crawlEndpoints.forEach(crawlEndpoint -> {
            crawlEndPointPersistenceService.delete(mappingId);
            crawlTaskPersistenceService.delete(crawlEndpoint.getEndpointId());
        });

        List<CrawlEndpointCommand> crawlEndpointCommands = siteProfileCommand.crawlEndpointCommands();

        crawlEndpointCommands.forEach(c ->{
            long endpointId = crawlEndPointPersistenceService.insert(c.toCrawlEndpointEntity(siteId, mappingId));
            c.crawlTasks().forEach(ct -> {
                CrawlTaskEntity crawlTaskEntity = ct.toCrawlTaskEntity(endpointId);
                crawlTaskPersistenceService.insert(crawlTaskEntity);
            });
        });
    }

}
