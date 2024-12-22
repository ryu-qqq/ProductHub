package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.db.core.product.dto.ProductProcessingResultDto;

public record ExternalProductProcessingResult(
        long productGroupId,
        ProductDataType productDataType,
        String result
) {

    public static ExternalProductProcessingResult from(ProductProcessingResultDto dto) {
        return new ExternalProductProcessingResult(
                dto.getProductGroupId(),
                dto.getProductDataType(),
                dto.getResult()
        );
    }
}
