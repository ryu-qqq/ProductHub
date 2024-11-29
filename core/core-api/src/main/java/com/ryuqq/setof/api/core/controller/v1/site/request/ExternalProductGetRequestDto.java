package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.enums.core.SearchKeyword;
import com.ryuqq.setof.enums.core.SyncStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ExternalProductGetRequestDto(
        SyncStatus syncStatus,
        Long siteId,
        Long categoryId,
        List<Long> brandIds,
        List<Long> colorIds,
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
) {
}
