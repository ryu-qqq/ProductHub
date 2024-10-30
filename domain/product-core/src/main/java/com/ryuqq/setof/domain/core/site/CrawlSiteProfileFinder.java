package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.CrawlType;
import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.domain.core.exception.NotFoundException;
import com.ryuqq.setof.storage.db.core.site.CrawlSiteQueryDslQueryRepository;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlAuthSettingDto;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlEndPointDto;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlSiteProfileDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ryuqq.setof.domain.core.site.SiteErrorConstants.SITE_PROFILE_NOT_FOUND_ERROR_MSG;

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
    public SiteProfile fetchSiteProfile(long siteId, SiteType siteType) {
        CrawlSiteProfileDto crawlSiteProfileDto = crawlSiteQueryDslQueryRepository.fetchSiteProfile(siteId, siteType)
                .orElseThrow(() -> new NotFoundException(SITE_PROFILE_NOT_FOUND_ERROR_MSG + siteId));
        return toCrawlSiteProfile(crawlSiteProfileDto);
    }


    public CrawlSiteProfile toCrawlSiteProfile(CrawlSiteProfileDto crawlSiteProfileDto){
        return new CrawlSiteProfile(
                toCrawlSetting(crawlSiteProfileDto.getCrawlFrequency(), crawlSiteProfileDto.getCrawlType()),
                toCrawlAuthSetting(crawlSiteProfileDto.getCrawlAuthSettingDto()),
                toCrawlEndPoints(crawlSiteProfileDto.getCrawlEndPointDtos())
        );
    }

    public CrawlSetting toCrawlSetting(int crawlFrequency, CrawlType crawlType){
        return new CrawlSetting(crawlFrequency, crawlType);
    }

    public CrawlAuthSetting toCrawlAuthSetting(CrawlAuthSettingDto crawlAuthSettingDto){
        return new CrawlAuthSetting(
                crawlAuthSettingDto.getAuthType(),
                crawlAuthSettingDto.getAuthEndpoint(),
                crawlAuthSettingDto.getAuthHeaders(),
                crawlAuthSettingDto.getAuthPayload()
        );
    }


    public List<CrawlEndpoint> toCrawlEndPoints(List<CrawlEndPointDto> crawlEndPointDtos){
        return crawlEndPointDtos.stream()
                .map(c -> new CrawlEndpoint(
                        c.getEndPointUrl(),
                        c.getCrawlFrequency()
                ))
                .toList();
    }



}