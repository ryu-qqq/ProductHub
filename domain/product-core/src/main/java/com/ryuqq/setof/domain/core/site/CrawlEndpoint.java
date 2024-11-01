package com.ryuqq.setof.domain.core.site;

import java.util.List;

public class CrawlEndpoint {

    private String endPointUrl;
    private String parameters;
    private List<CrawlTask> crawlTasks;

    public CrawlEndpoint(String endPointUrl, String parameters, List<CrawlTask> crawlTasks) {
        this.endPointUrl = endPointUrl;
        this.parameters =parameters;
        this.crawlTasks = crawlTasks;
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
