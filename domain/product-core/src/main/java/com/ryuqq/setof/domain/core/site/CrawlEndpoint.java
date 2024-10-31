package com.ryuqq.setof.domain.core.site;

import java.util.List;

public class CrawlEndpoint {

    private String endPointUrl;
    private List<CrawlTask> crawlTasks;

    public CrawlEndpoint(String endPointUrl, List<CrawlTask> crawlTasks) {
        this.endPointUrl = endPointUrl;
        this.crawlTasks = crawlTasks;
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public List<CrawlTask> getCrawlTasks() {
        return crawlTasks;
    }

}
