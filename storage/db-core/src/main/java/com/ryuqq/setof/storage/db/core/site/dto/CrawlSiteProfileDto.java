package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.core.CrawlType;
import com.ryuqq.setof.core.SiteType;

import java.util.List;

public class CrawlSiteProfileDto {

    private SiteType siteType;
    private int crawlFrequency;
    private CrawlType crawlType;
    private CrawlAuthSettingDto crawlAuthSettingDto;
    private List<CrawlEndPointDto> crawlEndPointDtos;

    @QueryProjection
    public CrawlSiteProfileDto(SiteType siteType, int crawlFrequency, CrawlType crawlType, CrawlAuthSettingDto crawlAuthSettingDto) {
        this.siteType = siteType;
        this.crawlFrequency = crawlFrequency;
        this.crawlType = crawlType;
        this.crawlAuthSettingDto = crawlAuthSettingDto;
    }

    public SiteType getSiteType() {
        return siteType;
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
