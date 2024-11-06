package com.ryuqq.setof.domain.core.site;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SiteContextCommandFacade {

    private final SiteQueryService siteQueryService;
    private final SiteCommandService siteCommandService;

    public SiteContextCommandFacade(SiteQueryService siteQueryService, SiteCommandService siteCommandService) {
        this.siteQueryService = siteQueryService;
        this.siteCommandService = siteCommandService;
    }

    @Transactional
    public long insert(SiteCommand siteCommand){
        boolean b = siteQueryService.siteExist(siteCommand.name(), siteCommand.baseUrl());
        if(b) throw new IllegalArgumentException(String.format("이미 존재하는 사이트 %s 입니다.", siteCommand.name()));
        return siteCommandService.insert(siteCommand.toSiteEntity());
    }
}
