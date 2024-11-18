package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.enums.core.SiteType;
import com.ryuqq.setof.domain.core.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static com.ryuqq.setof.domain.core.site.SiteErrorConstants.SITE_PROFILE_NOT_FOUND_ERROR_MSG;

@Service
public class SiteProfileCommandFacade {

    private final SiteQueryService siteQueryService;
    private final SiteProfileCommandProvider siteProfileCommandProvider;

    public SiteProfileCommandFacade(SiteQueryService siteQueryService, SiteProfileCommandProvider siteProfileCommandProvider) {
        this.siteQueryService = siteQueryService;
        this.siteProfileCommandProvider = siteProfileCommandProvider;
    }

    @Transactional
    public long insert(SiteType siteType, long siteId, SiteProfileCommand siteProfileCommand){
        boolean b = siteQueryService.siteExist(siteId);
        if(!b){
            throw new NotFoundException(SITE_PROFILE_NOT_FOUND_ERROR_MSG + siteId);
        }

        SiteProfileCommandService<SiteProfileCommand> siteProfileCommandSiteProfileCommandService =
                (SiteProfileCommandService<SiteProfileCommand>) siteProfileCommandProvider.get(siteType);
        siteProfileCommandSiteProfileCommandService.insert(siteId, siteProfileCommand);
        return siteId;

    }


    @Transactional
    public long update(SiteType siteType, long siteId, long mappingId, SiteProfileCommand siteProfileCommand){

        SiteProfileCommandService<SiteProfileCommand> siteProfileCommandSiteProfileCommandService =
                (SiteProfileCommandService<SiteProfileCommand>) siteProfileCommandProvider.get(siteType);

        siteProfileCommandSiteProfileCommandService.update(siteId, mappingId, siteProfileCommand);
        return mappingId;
    }





}
