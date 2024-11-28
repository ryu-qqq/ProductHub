package com.ryuqq.setof.api.core.controller.v1.product.request;

import com.ryuqq.setof.domain.core.product.gpt.DescriptionResult;
import com.ryuqq.setof.enums.core.ProductDataType;

public record BatchSaveDescriptionRequestDto(
        long productGroupId,
        String description
) implements BatchSaveRequest {

    public ProductDataType getDataType() {
        return ProductDataType.DESCRIPTION;
    }

    public DescriptionResult toDomain() {
        return new DescriptionResult(productGroupId, description);
    }

}
