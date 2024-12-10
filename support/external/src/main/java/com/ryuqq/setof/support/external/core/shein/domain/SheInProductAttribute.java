package com.ryuqq.setof.support.external.core.shein.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SheInProductAttribute(
         @JsonProperty("attribute_id") long attributeId,
         @JsonProperty("attribute_value_id") long attributeValueId
) {
}
