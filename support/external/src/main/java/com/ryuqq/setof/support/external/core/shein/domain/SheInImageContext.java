package com.ryuqq.setof.support.external.core.shein.domain;

import com.ryuqq.setof.support.external.core.ExternalMallImage;

public record SheInImageContext(
        Long imageItemId,
        int imageSort,
        int imageType,
        String imageUrl,
        String originUrl

) implements ExternalMallImage {

        @Override
        public String getImageUrl() {
                return imageUrl;
        }

        @Override
        public int getOrder() {
                return imageSort;
        }

        @Override
        public String getOriginUrl() {
                return originUrl;
        }

}
