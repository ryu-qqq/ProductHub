package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.ryuqq.setof.enums.core.SearchKeyword;
import com.ryuqq.setof.enums.core.SyncStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ExternalProductGroupStorageFilterDto(
        SyncStatus syncStatus,
        Long siteId,
        List<Long> categoryIds,
        List<Long> brandIds,
        List<Long> colorIds,
        List<Long> productGroupIds,
        Long sellerId,
        String styleCode,
        Boolean soldOutYn,
        Boolean displayYn,
        Long minSalePrice,
        Long maxSalePrice,
        Long minDiscountRate,
        Long maxDiscountRate,
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime startDate,
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime endDate,
        SearchKeyword searchKeyword,
        String searchWord,
        Integer pageSize,
        Integer pageNumber,
        Long cursorId
) {}
