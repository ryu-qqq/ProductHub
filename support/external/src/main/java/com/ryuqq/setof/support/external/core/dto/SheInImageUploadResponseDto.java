package com.ryuqq.setof.support.external.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class SheInImageUploadResponseDto {
    @JsonProperty("transformed")
    private final String imageUrl;

    public SheInImageUploadResponseDto(
            @JsonProperty("transformed")
            String imageUrl
    ) {
        this.imageUrl = imageUrl;
    }

        public String getImageUrl() {
                return imageUrl;
        }
}
