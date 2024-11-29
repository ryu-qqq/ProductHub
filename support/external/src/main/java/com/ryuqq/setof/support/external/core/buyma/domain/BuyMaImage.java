package com.ryuqq.setof.support.external.core.buyma.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryuqq.setof.support.external.core.ExternalMallProductImageContext;

public record BuyMaImage(
        @JsonProperty("path")
        String path,
        @JsonProperty("position")
        int position
) implements ExternalMallProductImageContext {
        @Override
        public int getDisplayOrder() {
                return position;
        }

        @Override
        public String getImageUrl() {
                return path;
        }

        @Override
        public String getOriginUrl() {
                return path;
        }
}
