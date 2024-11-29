package com.ryuqq.setof.api.core.controller.v1.product.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ryuqq.setof.domain.core.product.gpt.GptBatchResult;
import com.ryuqq.setof.enums.core.ProductDataType;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GptBatchSaveTitleRequestDto.class, name = "title"),
        @JsonSubTypes.Type(value = GptBatchSaveOptionsRequestDto.class, name = "options"),
        @JsonSubTypes.Type(value = GptBatchSaveDescriptionRequestDto.class, name = "description")
})
public interface GptBatchSaveRequest {
    ProductDataType getDataType();
    GptBatchResult toDomain();
}
