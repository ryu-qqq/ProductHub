package com.ryuqq.setof.domain.core.site.command;

import org.springframework.stereotype.Service;

@Service
public class SiteContextCommandFacade {

    private final SiteCommandService siteCommandService;
    private final SiteProfileCommandProvider siteProfileCommandProvider;

    public SiteContextCommandFacade(SiteCommandService siteCommandService, SiteProfileCommandProvider siteProfileCommandProvider) {
        this.siteCommandService = siteCommandService;
        this.siteProfileCommandProvider = siteProfileCommandProvider;
    }

    public long insert(SiteCommand siteCommand){
        long siteId = siteCommandService.insert(siteCommand.toSiteEntity());
        SiteProfileCommandService<SiteProfileCommand> siteProfileCommandSiteProfileCommandService =
                (SiteProfileCommandService<SiteProfileCommand>) siteProfileCommandProvider.get(siteCommand.siteType());
        siteProfileCommandSiteProfileCommandService.insert(siteId, siteCommand.siteProfileCommand());
        return siteId;
    }

}
