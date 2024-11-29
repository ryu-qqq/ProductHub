package com.ryuqq.setof.api.core.controller.v1.product.request;

import com.ryuqq.setof.domain.core.product.gpt.GptDescriptionResult;
import com.ryuqq.setof.enums.core.ProductDataType;

public record GptBatchSaveDescriptionRequestDto(
        long productGroupId,
        String description
) implements GptBatchSaveRequest {

    public ProductDataType getDataType() {
        return ProductDataType.DESCRIPTION;
    }

    public GptDescriptionResult toDomain() {
        return new GptDescriptionResult(productGroupId, description);
    }

}
