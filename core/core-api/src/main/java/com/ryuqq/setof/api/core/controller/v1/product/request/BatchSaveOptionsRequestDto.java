package com.ryuqq.setof.api.core.controller.v1.product.request;

import com.ryuqq.setof.domain.core.product.gpt.OptionsResult;
import com.ryuqq.setof.enums.core.ProductDataType;

import java.util.List;

public record BatchSaveOptionsRequestDto(
        long productGroupId,
        List<String> originalOptions,
        NormalizedOptions normalizedOptions
) implements BatchSaveRequest {

    public ProductDataType getDataType() {
        return ProductDataType.OPTIONS;
    }

    public record NormalizedOptions(
            List<String> sizes,
            String unit
    ) {}


    public OptionsResult toDomain() {
        return new OptionsResult(
                productGroupId,
                originalOptions,
                new OptionsResult.NormalizedOptions(
                        normalizedOptions.sizes(),
                        normalizedOptions.unit()
                )
        );
    }

}