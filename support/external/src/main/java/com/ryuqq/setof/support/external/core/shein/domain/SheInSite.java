package com.ryuqq.setof.support.external.core.shein.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SheInSite(
        @JsonProperty("main_site") String mainSite,
        @JsonProperty("sub_site_list") List<String> subSiteList
) {
}
