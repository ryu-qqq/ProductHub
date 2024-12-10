package com.ryuqq.setof.support.external.core.shein.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SheInAttributeRequestDto(
        @JsonProperty("product_type_id_list")
        List<Long> productTypeIds
) {}
