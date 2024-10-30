package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.storage.db.core.site.CrawlEndpointEntity;

public class CrawlEndpoint {

    private String endPointUrl;
    private int crawlFrequency;

    public CrawlEndpoint(String endPointUrl, int crawlFrequency) {
        this.endPointUrl = endPointUrl;
        this.crawlFrequency = crawlFrequency;
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public int getCrawlFrequency() {
        return crawlFrequency;
    }

    public CrawlEndpointEntity toCrawlEndpointEntity(long siteID) {
        return new CrawlEndpointEntity(siteID, endPointUrl, crawlFrequency);
    }

}
