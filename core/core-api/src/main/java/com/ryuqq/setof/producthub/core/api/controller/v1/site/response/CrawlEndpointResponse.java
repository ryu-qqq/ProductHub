package com.ryuqq.setof.producthub.core.api.controller.v1.site.response;

import com.ryuqq.setof.domain.core.site.CrawlEndpoint;

public record CrawlEndpointResponse(
        String endPointUrl,
        int crawlFrequency
) {

    public static CrawlEndpointResponse of(CrawlEndpoint crawlEndpoint) {
        return new CrawlEndpointResponse(
                crawlEndpoint.getEndPointUrl(),
                crawlEndpoint.getCrawlFrequency()
        );
    }
}
