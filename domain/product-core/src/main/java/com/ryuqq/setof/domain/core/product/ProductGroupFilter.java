package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.ManagementType;
import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupStorageFilterDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ProductGroupFilter(
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
)  {

    public ProductGroupStorageFilterDto toStorageFilterDto(){
        return new ProductGroupStorageFilterDto(productStatus, managementType, categoryIds, productGroupIds, brandIds, colorIds, sellerId, cursorId, styleCode, pageSize,
                soldOutYn, displayYn, minSalePrice, maxSalePrice, minDiscountRate, maxDiscountRate, startDate, endDate);
    }

    public static ProductGroupFilter of(List<Long> productGroupIds) {
        return new ProductGroupFilter(null, null, null, productGroupIds, List.of(), List.of(), null,
                null, null, productGroupIds.size(), null, null, null, null, null
        ,null, null, null);
    }




}
