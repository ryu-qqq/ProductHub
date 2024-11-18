package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.CrawlProductCommand;
import com.ryuqq.setof.enums.core.SiteName;

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
