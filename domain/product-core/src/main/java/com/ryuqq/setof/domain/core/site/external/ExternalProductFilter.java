package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.product.ProductGroupFilter;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductFilterDto;

import java.util.ArrayList;
import java.util.List;

public record ExternalProductFilter(
        Long siteId,
        Long sellerId,
        SyncStatus status,
        Long cursorId,
        int pageSize,
        List<Long> productGroupIds
) {

    public ExternalProductFilterDto toExternalProductFilterDto(){
        return new ExternalProductFilterDto(
                siteId,
                sellerId,
                status,
                cursorId,
                pageSize,
                productGroupIds
        );
    }

    public ProductGroupFilter withProductGroupIds(List<Long> newProductGroupIds) {
        List<Long> combinedIds = new ArrayList<>(this.productGroupIds != null ? this.productGroupIds : List.of());
        combinedIds.addAll(newProductGroupIds);

        return new ProductGroupFilter(null, null, List.of(), combinedIds,  List.of(),  List.of(), sellerId, cursorId, null, pageSize,
                null, null, null, null, null, null, null, null);
    }



}
