package com.ryuqq.setof.producthub.core.api.controller.v1.site.response;

import com.ryuqq.setof.domain.core.site.CrawlEndpoint;

import java.util.List;
import java.util.Map;

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
