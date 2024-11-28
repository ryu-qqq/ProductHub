package com.ryuqq.setof.domain.core.site.crawl;

import com.ryuqq.setof.storage.db.core.site.crawl.dto.CrawlProductStorageDto;

public record CrawlProductFilter(
        boolean isProductGroupIdNull,
        Long cursorId,
        Integer pageSize
) {

    public CrawlProductStorageDto toCrawlProductStorageDto(){
        return new CrawlProductStorageDto(isProductGroupIdNull, cursorId, pageSize);
    }
}
