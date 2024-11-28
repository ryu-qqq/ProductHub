package com.ryuqq.setof.api.core.controller.v1.product.request;


import com.ryuqq.setof.domain.core.site.external.ExternalProductName;
import com.ryuqq.setof.domain.core.product.gpt.TitleResult;
import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.ProductDataType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record BatchSaveTitleRequestDto(
        long productGroupId,
        String originalTitle,
        Map<String, String> filteredTitle,
        String brandName,
        String styleCode,
        String colorInTitle,
        List<String> deletedWords
) implements BatchSaveRequest {

    public ProductDataType getDataType() {
        return ProductDataType.TITLE;
    }

    public TitleResult toDomain() {
        List<ExternalProductName> externalProductNames = filteredTitle.entrySet()
                .stream()
                .map(entry -> new ExternalProductName(
                        Origin.valueOf(entry.getKey().toUpperCase()),
                        entry.getValue()
                ))
                .collect(Collectors.toList());

        return new TitleResult(
                productGroupId,
                originalTitle,
                externalProductNames,
                brandName,
                styleCode,
                colorInTitle,
                deletedWords
        );
    }
}
