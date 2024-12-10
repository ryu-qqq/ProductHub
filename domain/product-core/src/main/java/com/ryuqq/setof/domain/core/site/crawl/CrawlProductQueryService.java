package com.ryuqq.setof.domain.core.site.crawl;

import java.util.List;

public interface CrawlProductQueryService {

    List<CrawlProduct> fetchByFilter(CrawlProductFilter crawlProductFilter);
    long countByFilter(CrawlProductFilter crawlProductFilter);

}
