package com.ryuqq.setof.support.external.core.shein.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SheInImageGroupPayload(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("image_group_code")
        String imageGroupCode,
        @JsonProperty("image_info_list")
        List<SheInImagePayload> sheInImageContexts

) {
}
