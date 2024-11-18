package com.ryuqq.setof.api.core.controller.v1.site.response;

import com.ryuqq.setof.domain.core.site.CrawlProduct;
import com.ryuqq.setof.enums.core.SiteName;

public record CrawlProductResponse(
        long crawlProductId,
        long siteId,
        SiteName siteName,
        String siteProductId,
        String productName,
        Long productGroupId
)  {
    public static CrawlProductResponse toCrawlProductResponse(CrawlProduct crawlProduct){
        return new CrawlProductResponse(crawlProduct.getCrawlProductId(), crawlProduct.getSiteId(), SiteName.of(crawlProduct.getSiteName()), crawlProduct.getSiteProductId(), crawlProduct.getProductName(), crawlProduct.getProductGroupId());
    }

}