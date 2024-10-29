package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.core.ManagementType;
import com.ryuqq.setof.core.ProductStatus;
import com.ryuqq.setof.domain.core.product.ProductGroupFilter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ProductGroupGetRequestDto(
        ProductStatus productStatus,
        ManagementType managementType,
        Long categoryId,
        List<Long> productGroupIds,
        List<Long> brandIds,
        List<Long> colorIds,
        Long sellerId,
        Long cursorId,
        String styleCode,
        Integer pageSize,
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
) {

    public ProductGroupFilter toProductGroupFilter() {
        int defaultSize = (pageSize == null || pageSize == 0) ? 20 : pageSize;
        List<Long> defaultBrandIds = brandIds == null ? List.of() : brandIds;
        List<Long> defaultColorIds = colorIds == null ? List.of() : colorIds;
        List<Long> defaultProductGroupIds = productGroupIds == null ? List.of() : productGroupIds;

        return new ProductGroupFilter(productStatus, managementType, categoryId, defaultProductGroupIds, defaultBrandIds, defaultColorIds, sellerId, cursorId,
                styleCode, defaultSize, soldOutYn, displayYn, minSalePrice, maxSalePrice, minDiscountRate, maxDiscountRate, startDate, endDate);
    }

}
