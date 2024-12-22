package com.ryuqq.setof.support.external.core.oco.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public record OcoOptionDto(
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("product_option_id") Integer productOptionId,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("pid") Integer pid,
        @JsonProperty("option_data1") String optionData1,
        @JsonProperty("option_data2") String optionData2,
        @JsonProperty("option_product_count") int stockQuantity,
        @JsonProperty("option_use_yn") String optionUseYn,
        @JsonProperty("option_price") int optionPrice
) {
}
