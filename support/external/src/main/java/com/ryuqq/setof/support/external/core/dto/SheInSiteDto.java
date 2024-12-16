package com.ryuqq.setof.support.external.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SheInSiteDto(
        @JsonProperty("main_site") String mainSite,
        @JsonProperty("sub_site_list") List<String> subSiteList
) {
}
