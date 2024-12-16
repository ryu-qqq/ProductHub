package com.ryuqq.setof.support.external.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SheInProductAttributeDto(
         @JsonProperty("attribute_id") long attributeId,
         @JsonProperty("attribute_value_id") long attributeValueId
) {
}
