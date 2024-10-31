package com.ryuqq.setof.producthub.core.api.controller.v1.site.service;

import com.ryuqq.setof.domain.core.site.SiteContext;
import com.ryuqq.setof.domain.core.site.SiteQueryService;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.response.SiteContextResponse;
import org.springframework.stereotype.Service;

@Service
public class SiteQueryFacade {

    private final SiteQueryService siteQueryService;

    public SiteQueryFacade(SiteQueryService siteQueryService) {
        this.siteQueryService = siteQueryService;
    }

    public SiteContextResponse getSiteContextResponse(long siteId){
        SiteContext siteContext = siteQueryService.findSiteContext(siteId);
        return SiteContextResponse.of(siteContext);
    }

}
