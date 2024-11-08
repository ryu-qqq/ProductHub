package com.ryuqq.setof.producthub.core.api.controller.v1.site.request;

import com.ryuqq.setof.core.SiteName;
import com.ryuqq.setof.domain.core.site.CrawlProductCommand;

public record CrawlProductInsertRequestDto(
        long siteId,
        SiteName siteName,
        long siteProductId,
        String productName
) {

    public CrawlProductCommand toCrawlProductCommand(){
        return new CrawlProductCommand(siteId, siteName, siteProductId, productName);
    }

}
