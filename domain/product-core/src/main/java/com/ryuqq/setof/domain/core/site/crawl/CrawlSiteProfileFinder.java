package com.ryuqq.setof.domain.core.site.crawl;

import com.ryuqq.setof.domain.core.site.SiteProfileFinder;
import com.ryuqq.setof.enums.core.CrawlType;
import com.ryuqq.setof.enums.core.SiteType;
import com.ryuqq.setof.domain.core.exception.NotFoundException;
import com.ryuqq.setof.storage.db.core.site.crawl.CrawlSiteQueryDslQueryRepository;
import com.ryuqq.setof.storage.db.core.site.crawl.dto.CrawlAuthSettingDto;
import com.ryuqq.setof.storage.db.core.site.crawl.dto.CrawlEndPointDto;
import com.ryuqq.setof.storage.db.core.site.crawl.dto.CrawlSiteProfileDto;
import com.ryuqq.setof.storage.db.core.site.crawl.dto.CrawlTaskDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.ryuqq.setof.domain.core.site.SiteErrorConstants.SITE_PROFILE_NOT_FOUND_ERROR_MSG;

@Service
public class CrawlSiteProfileFinder implements SiteProfileFinder {

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
    public List<CrawlSiteProfile> fetchBySiteId(long siteId) {
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
    public CrawlSiteProfile fetchBySiteId(long siteId, long mappingId) {
        CrawlSiteProfileDto crawlSiteProfileDto = crawlSiteQueryDslQueryRepository.fetchSiteProfile(siteId, mappingId)
                .orElseThrow(() -> new NotFoundException(SITE_PROFILE_NOT_FOUND_ERROR_MSG + siteId));
        List<CrawlEndPointDto> crawlEndPointDtos = crawlSiteQueryDslQueryRepository.fetchCrawlEndPoints(siteId, mappingId);
        crawlSiteProfileDto.setCrawlEndPointDtos(crawlEndPointDtos);
        return toCrawlSiteProfile(mappingId, crawlSiteProfileDto);

    }

    private Map<Long, CrawlSiteProfileDto> fetchAndMapCrawlSiteProfiles(long siteId) {
        return crawlSiteQueryDslQueryRepository.fetchSiteProfile(siteId)
                .stream()
                .collect(Collectors.toMap(CrawlSiteProfileDto::getMappingId, Function.identity(), (existing, replacement) -> existing));
    }


    private Map<Long, List<CrawlEndPointDto>> fetchAndMapCrawlEndpoints(long siteId) {
        return crawlSiteQueryDslQueryRepository.fetchCrawlEndPoints(siteId, null)
                .stream()
                .collect(Collectors.groupingBy(CrawlEndPointDto::getMappingId));
    }

    private CrawlSiteProfile toCrawlSiteProfile(long mappingId, CrawlSiteProfileDto crawlSiteProfileDto){
        String randomConfigName = getRandomConfigurationName();
        Map<String, String> headers = userAgentCombinator.generateRandomHeaders(randomConfigName);

        return new CrawlSiteProfile(
                mappingId,
                crawlSiteProfileDto.getCrawlSettingId(),
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
                crawlAuthSettingDto.getAuthSettingId(),
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
                        c.getType(),
                        c.getTarget(),
                        c.getAction(),
                        c.getParams(),
                        c.getEndPointUrl(),
                        c.getResponseMapping()
                ))
                .toList();
    }


    private String getRandomConfigurationName() {
        Random random = new Random();
        return configurationNames.get(random.nextInt(configurationNames.size()));
    }




}
