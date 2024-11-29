package com.ryuqq.setof.storage.db.core.site.crawl.dto;

public record CrawlProductStorageDto(
        boolean isProductGroupIdNull,
        Long cursorId,
        Integer pageSize
) {}
