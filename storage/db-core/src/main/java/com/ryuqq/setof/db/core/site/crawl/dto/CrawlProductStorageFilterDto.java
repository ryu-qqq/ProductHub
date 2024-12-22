package com.ryuqq.setof.storage.db.core.site.crawl.dto;

public record CrawlProductStorageFilterDto(
        boolean isProductGroupIdNull,
        Long cursorId,
        Integer pageSize
) {}
