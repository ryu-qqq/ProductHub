package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.site.dto.CrawlProductDto;
import com.ryuqq.setof.storage.db.core.site.dto.CrawlProductStorageDto;

import java.util.List;

public interface CrawlProductQueryRepository {

    List<CrawlProductDto> fetchCrawlProducts(CrawlProductStorageDto crawlProductStorageDto);
    long fetchCrawlProductCount(CrawlProductStorageDto crawlProductStorageDto);
}
