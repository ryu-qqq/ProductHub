package com.ryuqq.setof.support.external.core.oco.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OcoOptionDto(
        @JsonProperty("option_data1") String optionData1,
        @JsonProperty("option_data2") String optionData2,
        @JsonProperty("option_product_count") int stockQuantity
) {
}
