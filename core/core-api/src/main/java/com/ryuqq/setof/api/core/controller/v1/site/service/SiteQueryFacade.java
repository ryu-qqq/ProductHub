package com.ryuqq.setof.api.core.controller.v1.site.service;

import com.ryuqq.setof.api.core.controller.v1.site.mapper.SiteSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.site.response.SiteContextResponse;
import com.ryuqq.setof.api.core.controller.v1.site.response.SiteResponse;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.site.Site;
import com.ryuqq.setof.domain.core.site.SiteContext;
import com.ryuqq.setof.domain.core.site.SiteFilter;
import com.ryuqq.setof.domain.core.site.SiteQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteQueryFacade {

    private final SiteSliceMapper siteSliceMapper;
    private final SiteQueryService siteQueryService;

    public SiteQueryFacade(SiteSliceMapper siteSliceMapper, SiteQueryService siteQueryService) {
        this.siteSliceMapper = siteSliceMapper;
        this.siteQueryService = siteQueryService;
    }

    public Slice<SiteResponse> getSiteResponses(SiteFilter siteFilter){
        List<Site> sites = siteQueryService.findSiteResponse(siteFilter);
        List<SiteResponse> siteResponse = sites.stream().map(SiteResponse::of).toList();
        long siteCount = siteQueryService.findSiteCount(siteFilter);
        return siteSliceMapper.toSlice(siteResponse, siteFilter.pageSize(), siteCount);
    }

    public SiteContextResponse getSiteContextResponse(long siteId){
        SiteContext siteContext = siteQueryService.findSiteContext(siteId);
        return SiteContextResponse.of(siteContext);
    }



}
