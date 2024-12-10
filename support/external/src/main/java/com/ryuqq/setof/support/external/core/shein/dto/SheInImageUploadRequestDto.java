package com.ryuqq.setof.support.external.core.shein.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SheInImageUploadRequestDto(
        @JsonProperty("image_type") int imageType,
        @JsonProperty("original_url") String originalUrl
) {}
