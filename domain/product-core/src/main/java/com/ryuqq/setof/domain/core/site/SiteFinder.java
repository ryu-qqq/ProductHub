package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.storage.db.core.site.SiteQueryRepository;
import com.ryuqq.setof.storage.db.core.site.dto.SiteContextDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SiteFinder implements SiteQueryService{

    private final SiteQueryRepository siteQueryRepository;
    private final SiteProfileFinderProvider siteProfileFinderProvider;
    private final SiteContextMapper siteContextMapper;

    public SiteFinder(SiteQueryRepository siteQueryRepository, SiteProfileFinderProvider siteProfileFinderProvider, SiteContextMapper siteContextMapper) {
        this.siteQueryRepository = siteQueryRepository;
        this.siteProfileFinderProvider = siteProfileFinderProvider;
        this.siteContextMapper = siteContextMapper;
    }

    @Override
    public boolean siteExist(long siteId) {
        return siteQueryRepository.fetchSiteExists(siteId);
    }

    @Override
    public boolean siteExist(String name, String baseUrl) {
        return siteQueryRepository.fetchSiteExists(name, baseUrl);
    }

    @Override
    public List<Site> findSiteResponse(SiteFilter siteFilter) {
        List<SiteContextDto> siteContextDtos = siteQueryRepository.fetchSites(siteFilter.toSiteFilterStorageDto());
        return siteContextDtos.stream()
                .map(s -> new Site(s.getSiteId(), s.getSiteName(), s.getBaseUrl(), s.getCountryCode(), s.getSiteType()))
                .toList();
    }

    @Override
    public long findSiteCount(SiteFilter siteFilter) {
        return siteQueryRepository.fetchSiteCount(siteFilter.toSiteFilterStorageDto());
    }

    @Override
    public SiteContext findSiteContext(long siteId){
        SiteContextDto siteContextDto = siteQueryRepository.fetchSiteContext(siteId).orElseThrow(RuntimeException::new);
        SiteProfileFinder siteProfileFinder = siteProfileFinderProvider.get(siteContextDto.getSiteType());
        SiteProfile siteProfile = siteProfileFinder.fetchSiteProfile(siteId, siteContextDto.getSiteType());
        return siteContextMapper.toSiteContext(siteContextDto, siteProfile);
    }

}
