package com.ryuqq.setof.api.core.controller.v1.product.request;

import com.ryuqq.setof.domain.core.product.ProductGroupFilter;
import com.ryuqq.setof.domain.core.site.external.ExternalProductFilter;
import com.ryuqq.setof.enums.core.ProductStatus;

import java.util.List;

public record GptTrainingDataFilterRequestDto(
        Long sellerId,
        Long siteId,
        Long cursorId,
        Integer pageSize
) {

    public ProductGroupFilter toProductGroupFilter(){
        int defaultSize = (pageSize == null || pageSize == 0) ? 20 : pageSize;

        return new ProductGroupFilter(ProductStatus.PROCESSING, null, null, List.of(), List.of(), List.of(), sellerId, cursorId, null, defaultSize,
                null, null, null, null, null, null, null, null);
    }

    public ExternalProductFilter toExternalFilter(List<Long> productGroupIds){
        return new ExternalProductFilter(
                siteId,
                null,
                null,
                null,
                productGroupIds.size(),
                productGroupIds
        );
    }
}
