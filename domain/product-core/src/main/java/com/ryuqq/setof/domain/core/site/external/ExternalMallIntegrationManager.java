package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.site.SiteContext;
import com.ryuqq.setof.domain.core.site.SiteQueryService;
import com.ryuqq.setof.domain.core.site.external.mapper.ExternalProductMapper;
import com.ryuqq.setof.domain.core.site.external.mapper.ExternalProductMapperProvider;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalMallIntegrationManager {

    private final SiteQueryService siteQueryService;
    private final ExternalProductDataFinder externalProductDataFinder;
    private final ExternalProductMapperProvider externalProductMapperProvider;


    public ExternalMallIntegrationManager(SiteQueryService siteQueryService, ExternalProductDataFinder externalProductDataFinder, ExternalProductMapperProvider externalProductMapperProvider) {
        this.siteQueryService = siteQueryService;
        this.externalProductDataFinder = externalProductDataFinder;
        this.externalProductMapperProvider = externalProductMapperProvider;
    }


    @Transactional
    public void integration(ExternalProductFilter externalProductFilter){
        SiteContext siteContext = siteQueryService.findSiteContext(externalProductFilter.siteId());
        List<ExternalProductUploadData> externalProductUploadData = externalProductDataFinder.findExternalProductUploadData(externalProductFilter);
        ExternalProductMapper externalProductMapper = externalProductMapperProvider.get(siteContext.getSiteNameEnum());


    }




}
