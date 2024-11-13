package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.storage.db.core.site.dto.CrawlProductStorageDto;

public record CrawlProductFilter(
        boolean isProductGroupIdNull,
        Long cursorId,
        Integer pageSize
) {

    public CrawlProductStorageDto toCrawlProductStorageDto(){
        return new CrawlProductStorageDto(isProductGroupIdNull, cursorId, pageSize);
    }
}
