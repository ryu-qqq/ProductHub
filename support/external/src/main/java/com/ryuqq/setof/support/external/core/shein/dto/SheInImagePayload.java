package com.ryuqq.setof.support.external.core.shein.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public record SheInImagePayload(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("image_item_id")
        Long imageItemId,
        @JsonProperty("image_sort")
        int imageSort,
        @JsonProperty("image_type")
        int imageType,
        @JsonProperty("image_url")
        String imageUrl
) {
}
