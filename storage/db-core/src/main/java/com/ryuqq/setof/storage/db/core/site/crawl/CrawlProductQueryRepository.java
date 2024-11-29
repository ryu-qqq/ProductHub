package com.ryuqq.setof.storage.db.core.site.crawl;

import com.ryuqq.setof.storage.db.core.site.crawl.dto.CrawlProductDto;
import com.ryuqq.setof.storage.db.core.site.crawl.dto.CrawlProductStorageDto;

import java.util.List;

public interface CrawlProductQueryRepository {

    List<CrawlProductDto> fetchCrawlProducts(CrawlProductStorageDto crawlProductStorageDto);
    long fetchCrawlProductCount(CrawlProductStorageDto crawlProductStorageDto);
}
