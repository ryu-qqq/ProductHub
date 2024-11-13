package com.ryuqq.setof.producthub.core.api.controller.v1.site.response;

import com.ryuqq.setof.core.SiteName;
import com.ryuqq.setof.domain.core.site.CrawlProduct;

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