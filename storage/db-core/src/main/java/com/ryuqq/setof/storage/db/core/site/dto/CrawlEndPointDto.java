package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.util.Comparator;
import java.util.List;

public class CrawlEndPointDto {
    private String endPointUrl;
    private String parameters;
    private List<CrawlTaskDto> crawlTaskDtos;

    @QueryProjection
    public CrawlEndPointDto(String endPointUrl, String parameters, List<CrawlTaskDto> crawlTaskDtos) {
        this.endPointUrl = endPointUrl;
        this.parameters =parameters;
        this.crawlTaskDtos = crawlTaskDtos.stream()
                .sorted(Comparator.comparingInt(CrawlTaskDto::getStepOrder))
                .toList();
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public String getParameters() {
        return parameters;
    }

    public List<CrawlTaskDto> getCrawlTaskDtos() {
        return crawlTaskDtos;
    }
}
