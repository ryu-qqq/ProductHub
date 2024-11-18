package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.CrawlType;

import java.util.ArrayList;
import java.util.List;

public class CrawlSiteProfileDto {

    private long mappingId;
    private long crawlSettingId;
    private int crawlFrequency;
    private CrawlType crawlType;
    private CrawlAuthSettingDto crawlAuthSettingDto;
    private List<CrawlEndPointDto> crawlEndPointDtos;

    @QueryProjection
    public CrawlSiteProfileDto(long mappingId, long crawlSettingId, int crawlFrequency, CrawlType crawlType, CrawlAuthSettingDto crawlAuthSettingDto) {
        this.mappingId = mappingId;
        this.crawlSettingId = crawlSettingId;
        this.crawlFrequency = crawlFrequency;
        this.crawlType = crawlType;
        this.crawlAuthSettingDto = crawlAuthSettingDto;
        this.crawlEndPointDtos = new ArrayList<>();
    }

    public long getMappingId() {
        return mappingId;
    }

    public long getCrawlSettingId() {
        return crawlSettingId;
    }

    public int getCrawlFrequency() {
        return crawlFrequency;
    }

    public CrawlType getCrawlType() {
        return crawlType;
    }

    public CrawlAuthSettingDto getCrawlAuthSettingDto() {
        return crawlAuthSettingDto;
    }

    public List<CrawlEndPointDto> getCrawlEndPointDtos() {
        return crawlEndPointDtos;
    }

    public void setCrawlEndPointDtos(List<CrawlEndPointDto> crawlEndPointDtos) {
        this.crawlEndPointDtos = crawlEndPointDtos;
    }



}
