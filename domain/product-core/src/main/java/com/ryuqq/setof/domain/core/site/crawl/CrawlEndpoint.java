package com.ryuqq.setof.domain.core.site.crawl;

import java.util.List;

public class CrawlEndpoint {
    private long endpointId;
    private String endPointUrl;
    private String parameters;
    private List<CrawlTask> crawlTasks;

    public CrawlEndpoint(long endpointId, String endPointUrl, String parameters, List<CrawlTask> crawlTasks) {
        this.endpointId = endpointId;
        this.endPointUrl = endPointUrl;
        this.parameters =parameters;
        this.crawlTasks = crawlTasks;
    }

    public long getEndpointId() {
        return endpointId;
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public List<CrawlTask> getCrawlTasks() {
        return crawlTasks;
    }

    public String getParameters() {
        return parameters;
    }
}
