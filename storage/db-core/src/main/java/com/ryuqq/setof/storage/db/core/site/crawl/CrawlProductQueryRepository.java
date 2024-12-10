package com.ryuqq.setof.storage.db.core.site.crawl;

import com.ryuqq.setof.storage.db.core.site.crawl.dto.CrawlProductDto;
import com.ryuqq.setof.storage.db.core.site.crawl.dto.CrawlProductStorageFilterDto;

import java.util.List;

public interface CrawlProductQueryRepository {

    List<CrawlProductDto> fetchByFilter(CrawlProductStorageFilterDto crawlProductStorageFilterDto);
    long countByFilter(CrawlProductStorageFilterDto crawlProductStorageFilterDto);
}
