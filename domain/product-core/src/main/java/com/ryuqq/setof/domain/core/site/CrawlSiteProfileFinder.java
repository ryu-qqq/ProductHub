package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.CrawlType;
import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.storage.db.core.site.CrawlSiteQueryDslQueryRepository;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlAuthSettingDto;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlEndPointDto;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlSiteProfileDto;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlTaskDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CrawlSiteProfileFinder implements SiteProfileFinder{

    private final CrawlSiteQueryDslQueryRepository crawlSiteQueryDslQueryRepository;

    public CrawlSiteProfileFinder(CrawlSiteQueryDslQueryRepository crawlSiteQueryDslQueryRepository) {
        this.crawlSiteQueryDslQueryRepository = crawlSiteQueryDslQueryRepository;
    }

    @Override
    public SiteType getSiteType() {
        return SiteType.CRAWL;
    }

    @Override
    public List<CrawlSiteProfile> fetchSiteProfile(long siteId, SiteType siteType) {
        Map<Long, CrawlSiteProfileDto> crawlProfileMap = fetchAndMapCrawlSiteProfiles(siteId, siteType);
        Map<Long, List<CrawlEndPointDto>> crawlEndPointMap = fetchAndMapCrawlEndpoints(siteId);

        crawlEndPointMap.forEach((aLong, crawlEndPointDto) -> {
            CrawlSiteProfileDto crawlSiteProfileDto = crawlProfileMap.get(aLong);
            if(crawlSiteProfileDto != null){
                crawlSiteProfileDto.setCrawlEndPointDtos(crawlEndPointDto);
            }
        });

        return crawlProfileMap.values().stream().map(this::toCrawlSiteProfile).toList();
    }

    private Map<Long, CrawlSiteProfileDto> fetchAndMapCrawlSiteProfiles(long siteId, SiteType siteType) {
        return crawlSiteQueryDslQueryRepository.fetchSiteProfile(siteId, siteType)
                .stream()
                .collect(Collectors.toMap(CrawlSiteProfileDto::getMappingId, Function.identity(), (existing, replacement) -> existing));
    }


    private Map<Long, List<CrawlEndPointDto>> fetchAndMapCrawlEndpoints(long siteId) {
        return crawlSiteQueryDslQueryRepository.fetchCrawlEndPoints(siteId)
                .stream()
                .collect(Collectors.groupingBy(CrawlEndPointDto::getMappingId));
    }

    private CrawlSiteProfile toCrawlSiteProfile(CrawlSiteProfileDto crawlSiteProfileDto){
        return new CrawlSiteProfile(
                toCrawlSetting(crawlSiteProfileDto.getCrawlFrequency(), crawlSiteProfileDto.getCrawlType()),
                toCrawlAuthSetting(crawlSiteProfileDto.getCrawlAuthSettingDto()),
                toCrawlEndPoints(crawlSiteProfileDto.getCrawlEndPointDtos())
        );
    }

    private CrawlSetting toCrawlSetting(int crawlFrequency, CrawlType crawlType){
        return new CrawlSetting(crawlFrequency, crawlType);
    }

    private CrawlAuthSetting toCrawlAuthSetting(CrawlAuthSettingDto crawlAuthSettingDto){
        return new CrawlAuthSetting(
                crawlAuthSettingDto.getAuthType(),
                crawlAuthSettingDto.getAuthEndpoint(),
                crawlAuthSettingDto.getAuthHeaders(),
                crawlAuthSettingDto.getAuthPayload()
        );
    }


    private List<CrawlEndpoint> toCrawlEndPoints(List<CrawlEndPointDto> crawlEndPointDtos){
        return crawlEndPointDtos.stream()
                .map(c -> new CrawlEndpoint(
                        c.getEndPointUrl(),
                        c.getParameters(),
                        toCrawlTasks(c.getCrawlTaskDtos())
                ))
                .toList();
    }

    private List<CrawlTask> toCrawlTasks(List<CrawlTaskDto> crawlTaskDtos){
        return crawlTaskDtos.stream()
                .map(c -> new CrawlTask(
                        c.getEndpointId(),
                        c.getStepOrder(),
                        c.getTaskType(),
                        c.getActionTarget(),
                        c.getActionType(),
                        c.getParams(),
                        c.getResponseMapping()
                ))
                .toList();
    }


}
