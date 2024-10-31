package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.util.List;

public class CrawlEndPointDto {
    private String endPointUrl;
    private List<CrawlTaskDto> crawlTaskDtos;

    @QueryProjection
    public CrawlEndPointDto(String endPointUrl, List<CrawlTaskDto> crawlTaskDtos) {
        this.endPointUrl = endPointUrl;
        this.crawlTaskDtos = crawlTaskDtos;
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public List<CrawlTaskDto> getCrawlTaskDtos() {
        return crawlTaskDtos;
    }
}
