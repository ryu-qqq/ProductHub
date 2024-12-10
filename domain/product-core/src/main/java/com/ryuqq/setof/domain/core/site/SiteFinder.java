package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.storage.db.core.site.SiteQueryRepository;
import com.ryuqq.setof.storage.db.core.site.dto.SiteContextDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SiteFinder implements SiteQueryService{

    private final SiteQueryRepository siteQueryRepository;
    private final SiteProfileFinderProvider siteProfileFinderProvider;

    public SiteFinder(SiteQueryRepository siteQueryRepository, SiteProfileFinderProvider siteProfileFinderProvider) {
        this.siteQueryRepository = siteQueryRepository;
        this.siteProfileFinderProvider = siteProfileFinderProvider;
    }

    @Override
    public boolean siteExist(long siteId) {
        return siteQueryRepository.existById(siteId);
    }

    @Override
    public boolean siteExist(String name, String baseUrl) {
        return siteQueryRepository.existByNameAndUrl(name, baseUrl);
    }

    @Override
    public List<Site> findSiteResponse(SiteFilter siteFilter) {
        List<SiteContextDto> siteContextDtos = siteQueryRepository.fetchByFilter(siteFilter.toSiteFilterStorageDto());
        return siteContextDtos.stream()
                .map(s -> new Site(s.getSiteId(), s.getSiteName(), s.getBaseUrl(), s.getCountryCode(), s.getSiteType()))
                .toList();
    }

    @Override
    public long findSiteCount(SiteFilter siteFilter) {
        return siteQueryRepository.countByFilter(siteFilter.toSiteFilterStorageDto());
    }

    @Override
    public SiteContext findSiteContext(long siteId){
        SiteContextDto siteContextDto = siteQueryRepository.fetchSiteContext(siteId).orElseThrow(RuntimeException::new);
        SiteProfileFinder siteProfileFinder = siteProfileFinderProvider.get(siteContextDto.getSiteType());
        List<? extends SiteProfile> siteProfiles = siteProfileFinder.fetchBySiteId(siteId);
        List<SiteProfile> castedSiteProfiles = (List<SiteProfile>) siteProfiles;
        return toSiteContext(siteContextDto, castedSiteProfiles);
    }

    private SiteContext toSiteContext(SiteContextDto siteContextDto, List<SiteProfile> siteProfiles){
        return new SiteContext(
                siteContextDto.getSiteId(),
                siteContextDto.getSiteName(),
                siteContextDto.getBaseUrl(),
                siteContextDto.getCountryCode(),
                siteContextDto.getSiteType(),
                siteProfiles
        );
    }

}
