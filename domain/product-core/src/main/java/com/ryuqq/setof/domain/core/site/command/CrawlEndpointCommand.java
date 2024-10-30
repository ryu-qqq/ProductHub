package com.ryuqq.setof.domain.core.site.command;

import com.ryuqq.setof.storage.db.core.site.CrawlEndpointEntity;

public record CrawlEndpointCommand(
        String endPointUrl,
        int crawlFrequency
) {

    public CrawlEndpointEntity toCrawlEndpointEntity(long siteId) {
        return new CrawlEndpointEntity(siteId, endPointUrl, crawlFrequency);
    }

}
