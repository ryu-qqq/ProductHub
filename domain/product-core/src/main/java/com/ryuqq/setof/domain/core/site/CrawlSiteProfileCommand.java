package com.ryuqq.setof.domain.core.site;

import java.util.List;

public record CrawlSiteProfileCommand(
        CrawlSettingCommand crawlSettingCommand,
        CrawlAuthSettingCommand crawlAuthSettingCommand,
        List<CrawlEndpointCommand> crawlEndpointCommands

) implements SiteProfileCommand {}
