package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.storage.db.core.site.SiteQueryRepository;
import com.ryuqq.setof.storage.db.core.site.dto.SiteContextDto;
import org.springframework.stereotype.Component;

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
    public SiteContext findSiteContext(long siteId, SiteType siteType){
        SiteContextDto siteContextDto = siteQueryRepository.fetchSiteContext(siteId, siteType).orElseThrow(RuntimeException::new);
        SiteProfileFinder siteProfileFinder = siteProfileFinderProvider.get(siteType);
        SiteProfile siteProfile = siteProfileFinder.fetchSiteProfile(siteId, siteType);
        return siteContextMapper.toSiteContext(siteContextDto, siteProfile);
    }

}
