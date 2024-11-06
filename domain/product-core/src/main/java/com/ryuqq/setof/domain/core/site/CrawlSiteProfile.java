package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.SiteType;

import java.util.List;
import java.util.Map;

public class CrawlSiteProfile implements SiteProfile {
    private long mappingId;
    private CrawlSetting crawlSetting;
    private CrawlAuthSetting crawlAuthSetting;
    private List<CrawlEndpoint> crawlEndpoints;
    private Map<String, String> headers;

    @Override
    public SiteType getSiteType() {
        return SiteType.CRAWL;
    }

    public CrawlSiteProfile(long mappingId, CrawlSetting crawlSetting, CrawlAuthSetting crawlAuthSetting, List<CrawlEndpoint> crawlEndpoints, Map<String, String> headers) {
        this.mappingId = mappingId;
        this.crawlSetting = crawlSetting;
        this.crawlAuthSetting = crawlAuthSetting;
        this.crawlEndpoints = crawlEndpoints;
        this.headers = headers;
    }

    public long getMappingId() {
        return mappingId;
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

    public Map<String, String> getHeaders() {
        return headers;
    }
}
