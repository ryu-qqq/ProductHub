package com.ryuqq.setof.domain.core.site.command;

import com.ryuqq.setof.storage.db.core.site.CrawlEndpointEntity;

import java.util.List;

public record CrawlEndpointCommand(
        String endPointUrl,
        String parameters,
        List<CrawlTaskCommand> crawlTasks
) {

    public CrawlEndpointEntity toCrawlEndpointEntity(long siteId, long mappingId) {
        return new CrawlEndpointEntity(siteId, mappingId, parameters, endPointUrl);
    }

}
