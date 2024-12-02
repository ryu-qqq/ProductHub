package com.ryuqq.setof.support.external.core.buyma.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryuqq.setof.support.external.core.ExternalMallImageContext;

public record BuyMaImageContext(
        @JsonProperty("path")
        String path,
        @JsonProperty("position")
        int position
) implements ExternalMallImageContext {

        @Override
        public String getImageUrl() {
                return path;
        }

        @Override
        public int getOrder() {
                return position;
        }

        @Override
        public String getOriginUrl() {
                return path;
        }
}
