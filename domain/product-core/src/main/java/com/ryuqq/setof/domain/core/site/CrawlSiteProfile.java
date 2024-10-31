package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.SiteType;

import java.util.List;

public class CrawlSiteProfile implements SiteProfile{

    private CrawlSetting crawlSetting;
    private CrawlAuthSetting crawlAuthSetting;
    private List<CrawlEndpoint> crawlEndpoints;

    @Override
    public SiteType getSiteType() {
        return SiteType.CRAWL;
    }

    public CrawlSiteProfile(CrawlSetting crawlSetting, CrawlAuthSetting crawlAuthSetting, List<CrawlEndpoint> crawlEndpoints) {
        this.crawlSetting = crawlSetting;
        this.crawlAuthSetting = crawlAuthSetting;
        this.crawlEndpoints = crawlEndpoints;
    }

    public CrawlSetting getCrawlSetting() {
        return crawlSetting;
    }

    public CrawlAuthSetting getCrawlAuthSetting() {
        return crawlAuthSetting;
    }

    public List<CrawlEndpoint> getCrawlEndpoints() {
        return crawlEndpoints;
    }


}
