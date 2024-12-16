package com.ryuqq.setof.support.external.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SheInAttributeResponse(
        @JsonProperty("data")
        List<SheInAttribute> data
) {
        public record SheInAttribute(
                @JsonProperty("product_type_id")
                long productTypeId,
                @JsonProperty("business_mode")
                long businessMode,
                @JsonProperty("attribute_infos")
                List<AttributeInfo> attributeInfos
        ){
                public record AttributeInfo(
                        @JsonProperty("attribute_id")
                        long attributeId,
                        @JsonProperty("attribute_name")
                        String attributeName,

                        @JsonProperty("attribute_value_info_list")
                        List<AttributeValueInfo> attributeValueInfos

                ) {
                        public record AttributeValueInfo(
                                @JsonProperty("attribute_value_id")
                                long attributeValueId,
                                @JsonProperty("attribute_label")
                                long attributeLabel,
                                @JsonProperty("attribute_value")
                                String attributeValueEn
                        ) {}
                }
        }


}