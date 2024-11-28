package com.ryuqq.setof.api.core.controller.v1.site.response;

import com.ryuqq.setof.domain.core.site.crawl.CrawlEndpoint;

import java.util.List;

public record CrawlEndpointResponse(
        long endpointId,
        String endPointUrl,
        String parameters,
        List<CrawlTaskResponse> crawlTasks

) {

    public static CrawlEndpointResponse of(CrawlEndpoint crawlEndpoint) {
        return new CrawlEndpointResponse(
                crawlEndpoint.getEndpointId(),
                crawlEndpoint.getEndPointUrl(),
                crawlEndpoint.getParameters(),
                crawlEndpoint.getCrawlTasks().stream().map(CrawlTaskResponse::of).toList()
        );
    }

}
