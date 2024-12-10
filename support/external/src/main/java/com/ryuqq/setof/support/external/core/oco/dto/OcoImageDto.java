package com.ryuqq.setof.support.external.core.oco.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record OcoImageDto(
        @JsonProperty("relativepath")
        String relativePath,
        @JsonIgnore
        int order
) {}
