package com.ryuqq.setof.storage.db.core.product.dto;

import com.ryuqq.setof.core.ManagementType;
import com.ryuqq.setof.core.ProductStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ProductGroupStorageFilterDto(
        ProductStatus productStatus,
        ManagementType managementType,
        List<Long> categoryIds,
        List<Long> productGroupIds,
        List<Long> brandIds,
        List<Long> colorIds,
        Long sellerId,
        Long cursorId,
        String styleCode,
        int pageSize,
        Boolean soldOutYn,
        Boolean displayYn,
        Long minSalePrice,
        Long maxSalePrice,
        Long minDiscountRate,
        Long maxDiscountRate,
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime startDate,
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime endDate
) {}
