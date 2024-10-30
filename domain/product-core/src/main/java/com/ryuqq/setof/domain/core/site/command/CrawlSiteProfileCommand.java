package com.ryuqq.setof.domain.core.site.command;

import java.util.List;

public record CrawlSiteProfileCommand(
        CrawlSettingCommand crawlSettingCommand,
        CrawlAuthSettingCommand crawlAuthSettingCommand,
        List<CrawlEndpointCommand> crawlEndpointCommands

) implements SiteProfileCommand {}
