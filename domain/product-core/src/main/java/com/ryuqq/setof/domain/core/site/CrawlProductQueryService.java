package com.ryuqq.setof.domain.core.site;

import java.util.List;

public interface CrawlProductQueryService {

    List<CrawlProduct> getCrawlProducts(CrawlProductFilter crawlProductFilter);
    long getCrawlProductCount(CrawlProductFilter crawlProductFilter);

}
