package com.ryuqq.setof.api.core.controller.v1.product.request;

import com.ryuqq.setof.domain.core.product.gpt.GptOptionsResult;
import com.ryuqq.setof.enums.core.ProductDataType;

import java.util.List;

public record GptBatchSaveOptionsRequestDto(
        long productGroupId,
        List<String> originalOptions,
        NormalizedOptions normalizedOptions
) implements GptBatchSaveRequest {

    public ProductDataType getDataType() {
        return ProductDataType.OPTIONS;
    }

    public record NormalizedOptions(
            List<String> sizes,
            String unit
    ) {}


    public GptOptionsResult toDomain() {
        return new GptOptionsResult(
                productGroupId,
                originalOptions,
                new GptOptionsResult.NormalizedOptions(
                        normalizedOptions.sizes(),
                        normalizedOptions.unit()
                )
        );
    }

}