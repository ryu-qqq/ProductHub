package com.ryuqq.setof.producthub.core.api.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.CrawlProductFilter;

public record CrawlProductGetRequestDto(
        String siteName,
        boolean isProductGroupIdNull,
        Long cursorId,
        Integer pageSize
) {
    public CrawlProductFilter toCrawlProductFilter(){
        int defaultSize = (pageSize == null || pageSize == 0) ? 20 : pageSize;
        return new CrawlProductFilter(isProductGroupIdNull, cursorId, defaultSize);
    }
}
