package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.CrawlType;
import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.domain.core.exception.NotFoundException;
import com.ryuqq.setof.storage.db.core.site.CrawlSiteQueryDslQueryRepository;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlAuthSettingDto;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlEndPointDto;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlSiteProfileDto;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlTaskDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.ryuqq.setof.domain.core.site.SiteErrorConstants.SITE_PROFILE_NOT_FOUND_ERROR_MSG;

@Service
public class CrawlSiteProfileFinder implements SiteProfileFinder{

    private final UserAgentCombinator userAgentCombinator;
    private final CrawlSiteQueryDslQueryRepository crawlSiteQueryDslQueryRepository;
    private final List<String> configurationNames = List.of("Mobile-Friendly", "Desktop-Heavy");

    public CrawlSiteProfileFinder(UserAgentCombinator userAgentCombinator, CrawlSiteQueryDslQueryRepository crawlSiteQueryDslQueryRepository) {
        this.userAgentCombinator = userAgentCombinator;
        this.crawlSiteQueryDslQueryRepository = crawlSiteQueryDslQueryRepository;
    }

    @Override
    public SiteType getSiteType() {
        return SiteType.CRAWL;
    }

    @Override
    public List<CrawlSiteProfile> fetchSiteProfile(long siteId) {
        Map<Long, CrawlSiteProfileDto> crawlProfileMap = fetchAndMapCrawlSiteProfiles(siteId);
        Map<Long, List<CrawlEndPointDto>> crawlEndPointMap = fetchAndMapCrawlEndpoints(siteId);

        crawlEndPointMap.forEach((aLong, crawlEndPointDto) -> {
            CrawlSiteProfileDto crawlSiteProfileDto = crawlProfileMap.get(aLong);
            if(crawlSiteProfileDto != null){
                crawlSiteProfileDto.setCrawlEndPointDtos(crawlEndPointDto);
            }
        });

        return crawlProfileMap.entrySet()
                .stream()
                .map(entry -> toCrawlSiteProfile(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Override
    public CrawlSiteProfile fetchSiteProfile(long siteId, long mappingId) {
        CrawlSiteProfileDto crawlSiteProfileDto = crawlSiteQueryDslQueryRepository.fetchSiteProfile(siteId, mappingId)
                .orElseThrow(() -> new NotFoundException(SITE_PROFILE_NOT_FOUND_ERROR_MSG + siteId));
        return toCrawlSiteProfile(mappingId, crawlSiteProfileDto);

    }

    private Map<Long, CrawlSiteProfileDto> fetchAndMapCrawlSiteProfiles(long siteId) {
        return crawlSiteQueryDslQueryRepository.fetchSiteProfile(siteId)
                .stream()
                .collect(Collectors.toMap(CrawlSiteProfileDto::getMappingId, Function.identity(), (existing, replacement) -> existing));
    }


    private Map<Long, List<CrawlEndPointDto>> fetchAndMapCrawlEndpoints(long siteId) {
        return crawlSiteQueryDslQueryRepository.fetchCrawlEndPoints(siteId)
                .stream()
                .collect(Collectors.groupingBy(CrawlEndPointDto::getMappingId));
    }

    private CrawlSiteProfile toCrawlSiteProfile(long mappingId, CrawlSiteProfileDto crawlSiteProfileDto){
        String randomConfigName = getRandomConfigurationName();
        Map<String, String> headers = userAgentCombinator.generateRandomHeaders(randomConfigName);

        return new CrawlSiteProfile(
                mappingId,
                toCrawlSetting(crawlSiteProfileDto.getCrawlFrequency(), crawlSiteProfileDto.getCrawlType()),
                toCrawlAuthSetting(crawlSiteProfileDto.getCrawlAuthSettingDto()),
                toCrawlEndPoints(crawlSiteProfileDto.getCrawlEndPointDtos()),
                headers
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
                        c.getEndpointId(),
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


    private String getRandomConfigurationName() {
        Random random = new Random();
        return configurationNames.get(random.nextInt(configurationNames.size()));
    }




}
