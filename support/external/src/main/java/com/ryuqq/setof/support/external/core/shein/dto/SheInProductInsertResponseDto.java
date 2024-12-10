package com.ryuqq.setof.support.external.core.shein.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SheInProductInsertResponseDto(
        @JsonProperty("success") boolean success,
        @JsonProperty("spu_name") String spuName
) {}
