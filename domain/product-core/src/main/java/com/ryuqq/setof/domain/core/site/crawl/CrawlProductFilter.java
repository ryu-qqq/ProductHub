package com.ryuqq.setof.domain.core.site.crawl;

import com.ryuqq.setof.db.core.site.crawl.dto.CrawlProductStorageFilterDto;

public record CrawlProductFilter(
        boolean isProductGroupIdNull,
        Long cursorId,
        Integer pageSize
) {

    public CrawlProductStorageFilterDto toCrawlProductStorageDto(){
        return new CrawlProductStorageFilterDto(isProductGroupIdNull, cursorId, pageSize);
    }
}
