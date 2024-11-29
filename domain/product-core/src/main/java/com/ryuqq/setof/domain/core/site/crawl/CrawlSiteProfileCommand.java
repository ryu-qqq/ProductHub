package com.ryuqq.setof.domain.core.site.crawl;

import com.ryuqq.setof.domain.core.site.SiteProfileCommand;

import java.util.List;

public record CrawlSiteProfileCommand(
        CrawlSettingCommand crawlSettingCommand,
        CrawlAuthSettingCommand crawlAuthSettingCommand,
        List<CrawlEndpointCommand> crawlEndpointCommands

) implements SiteProfileCommand {}
