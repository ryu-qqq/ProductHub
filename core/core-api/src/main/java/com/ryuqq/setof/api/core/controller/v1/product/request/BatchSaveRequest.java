package com.ryuqq.setof.api.core.controller.v1.product.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ryuqq.setof.domain.core.product.gpt.BatchResult;
import com.ryuqq.setof.enums.core.ProductDataType;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BatchSaveTitleRequestDto.class, name = "title"),
        @JsonSubTypes.Type(value = BatchSaveOptionsRequestDto.class, name = "options"),
        @JsonSubTypes.Type(value = BatchSaveDescriptionRequestDto.class, name = "description")
})
public interface BatchSaveRequest {
    ProductDataType getDataType();
    BatchResult toDomain();
}
