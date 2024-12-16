package com.ryuqq.setof.api.core.controller.v1.site.service;

import com.ryuqq.setof.api.core.controller.v1.site.request.ExternalProductGroupGetRequestDto;

import java.util.List;

public class ExternalProductGroupFilterRequest {

    private final ExternalProductGroupGetRequestDto originalRequest;
    private List<Long> processedCategoryIds;

    public ExternalProductGroupFilterRequest(ExternalProductGroupGetRequestDto originalRequest) {
        this.originalRequest = originalRequest;
    }

    public ExternalProductGroupGetRequestDto getOriginalRequest() {
        return originalRequest;
    }

    public List<Long> getProcessedCategoryIds() {
        return processedCategoryIds;
    }

    public void setProcessedCategoryIds(List<Long> processedCategoryIds) {
        this.processedCategoryIds = processedCategoryIds;
    }

    public ExternalProductGroupGetRequestDto toDto() {
        return new ExternalProductGroupGetRequestDto(
                originalRequest.syncStatus(),
                originalRequest.siteId(),
                processedCategoryIds,
                originalRequest.brandIds(),
                originalRequest.colorIds(),
                originalRequest.productGroupIds(),
                originalRequest.sellerId(),
                originalRequest.styleCode(),
                originalRequest.soldOutYn(),
                originalRequest.displayYn(),
                originalRequest.minSalePrice(),
                originalRequest.maxSalePrice(),
                originalRequest.minDiscountRate(),
                originalRequest.maxDiscountRate(),
                originalRequest.startDate(),
                originalRequest.endDate(),
                originalRequest.searchKeyword(),
                originalRequest.searchWord(),
                originalRequest.pageSize(),
                originalRequest.pageNumber(),
                originalRequest.cursorId()
        );
    }
}
