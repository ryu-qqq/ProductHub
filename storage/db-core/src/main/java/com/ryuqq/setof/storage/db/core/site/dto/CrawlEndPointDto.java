package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.util.Comparator;
import java.util.List;

public class CrawlEndPointDto {

    private long mappingId;
    private String endPointUrl;
    private String parameters;
    private List<CrawlTaskDto> crawlTaskDtos;

    @QueryProjection
    public CrawlEndPointDto(long mappingId, String endPointUrl, String parameters, List<CrawlTaskDto> crawlTaskDtos) {
        this.mappingId = mappingId;
        this.endPointUrl = endPointUrl;
        this.parameters =parameters;
        this.crawlTaskDtos = crawlTaskDtos.stream()
                .sorted(Comparator.comparingInt(CrawlTaskDto::getStepOrder))
                .toList();
    }

    public long getMappingId() {
        return mappingId;
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
