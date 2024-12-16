package com.ryuqq.setof.api.core.controller.v1.product.service;

import com.ryuqq.setof.api.core.controller.v1.product.request.ProductGroupGetRequestDto;

import java.util.List;

public class ProductGroupFilterRequest {
    private final ProductGroupGetRequestDto originalRequest;
    private List<Long> processedCategoryIds;

    public ProductGroupFilterRequest(ProductGroupGetRequestDto originalRequest) {
        this.originalRequest = originalRequest;
    }

    public ProductGroupGetRequestDto getOriginalRequest() {
        return originalRequest;
    }

    public List<Long> getProcessedCategoryIds() {
        return processedCategoryIds;
    }

    public void setProcessedCategoryIds(List<Long> processedCategoryIds) {
        this.processedCategoryIds = processedCategoryIds;
    }

    public ProductGroupGetRequestDto toDto() {
        return new ProductGroupGetRequestDto(
                originalRequest.productStatus(),
                originalRequest.syncStatus(),
                originalRequest.managementType(),
                processedCategoryIds,
                originalRequest.productGroupIds(),
                originalRequest.brandIds(),
                originalRequest.colorIds(),
                originalRequest.sellerId(),
                originalRequest.cursorId(),
                originalRequest.styleCode(),
                originalRequest.pageSize(),
                originalRequest.soldOutYn(),
                originalRequest.displayYn(),
                originalRequest.minSalePrice(),
                originalRequest.maxSalePrice(),
                originalRequest.minDiscountRate(),
                originalRequest.maxDiscountRate(),
                originalRequest.startDate(),
                originalRequest.endDate(),
                originalRequest.searchKeyword(),
                originalRequest.searchWord()
        );
    }
}
