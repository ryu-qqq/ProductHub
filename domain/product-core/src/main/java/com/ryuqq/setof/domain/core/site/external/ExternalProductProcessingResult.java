package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductProcessingResultDto;

public record ExternalProductProcessingResult(
        long productGroupId,
        ProductDataType productDataType,
        String result
) {

    public static ExternalProductProcessingResult from(ExternalProductProcessingResultDto dto) {
        return new ExternalProductProcessingResult(
                dto.getProductGroupId(),
                dto.getProductDataType(),
                dto.getResult()
        );
    }
}
