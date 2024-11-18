package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ryuqq.setof.domain.core.site.SiteProfileCommand;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "siteType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CrawlSiteProfileRequestDto.class, name = "CRAWL")
})
public interface SiteProfileRequestDto {
    SiteProfileCommand toSiteProfileCommand();
}
