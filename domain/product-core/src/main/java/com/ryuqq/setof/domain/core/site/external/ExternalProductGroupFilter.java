package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.SearchKeyword;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.db.core.site.external.dto.ExternalProductGroupStorageFilterDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ExternalProductGroupFilter(
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
) {

    public ExternalProductGroupFilter(SyncStatus syncStatus, Long siteId, List<Long> categoryIds, List<Long> brandIds, List<Long> colorIds, List<Long> productGroupIds, Long sellerId, String styleCode, Boolean soldOutYn, Boolean displayYn, Long minSalePrice, Long maxSalePrice, Long minDiscountRate, Long maxDiscountRate, LocalDateTime startDate, LocalDateTime endDate, SearchKeyword searchKeyword, String searchWord, Integer pageSize, Integer pageNumber, Long cursorId) {
        this.syncStatus = syncStatus;
        this.siteId = siteId;
        this.categoryIds = categoryIds;
        this.brandIds = brandIds;
        this.colorIds = colorIds;
        this.productGroupIds = productGroupIds;
        this.sellerId = sellerId;
        this.styleCode = styleCode;
        this.soldOutYn = soldOutYn;
        this.displayYn = displayYn;
        this.minSalePrice = minSalePrice;
        this.maxSalePrice = maxSalePrice;
        this.minDiscountRate = minDiscountRate;
        this.maxDiscountRate = maxDiscountRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.searchKeyword = searchKeyword;
        this.searchWord = searchWord;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.cursorId = cursorId;
    }

    public static ExternalProductGroupFilter of(long siteId, long sellerId, SyncStatus status, int pageSize) {

        return new ExternalProductGroupFilter(
                status, siteId, List.of(), List.of(), List.of(), List.of(), sellerId, null, null, null,
                null, null, null, null, null, null, null, null, pageSize, null, null
        );
    }

    public ExternalProductGroupStorageFilterDto toStorageFilterDto() {
        return new ExternalProductGroupStorageFilterDto(
                syncStatus, siteId, categoryIds, brandIds, colorIds, productGroupIds, sellerId, styleCode, soldOutYn, displayYn,
                minSalePrice, maxSalePrice, minDiscountRate, maxDiscountRate, startDate, endDate, searchKeyword, searchWord, pageSize, pageNumber, cursorId
        );
    }
}
