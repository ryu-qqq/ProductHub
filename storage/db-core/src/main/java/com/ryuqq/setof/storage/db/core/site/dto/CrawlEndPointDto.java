package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;

public class CrawlEndPointDto {
    private String endPointUrl;
    private int crawlFrequency;

    @QueryProjection
    public CrawlEndPointDto(String endPointUrl, int crawlFrequency) {
        this.endPointUrl = endPointUrl;
        this.crawlFrequency = crawlFrequency;
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public int getCrawlFrequency() {
        return crawlFrequency;
    }
}
