package com.ryuqq.setof.storage.db.core.site.dto;

public record CrawlProductStorageDto(
        boolean isProductGroupIdNull,
        Long cursorId,
        Integer pageSize
) {}
