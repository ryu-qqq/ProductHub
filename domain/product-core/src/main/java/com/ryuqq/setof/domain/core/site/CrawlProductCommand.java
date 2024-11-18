package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.storage.db.core.site.CrawlProductEntity;

public record CrawlProductCommand(
        long siteId,
        SiteName siteName,
        long siteProductId,
        String productName
) {
    public CrawlProductEntity toCrawlProductEntity(){
        return new CrawlProductEntity(siteId, productName, String.valueOf(siteProductId));
    }

}
