package com.ryuqq.setof.producthub.core.api.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.CrawlEndpointCommand;
import com.ryuqq.setof.domain.core.site.CrawlSiteProfileCommand;
import jakarta.validation.Valid;

import java.util.List;

public record CrawlSiteProfileRequestDto(
        @Valid
        CrawlSettingRequestDto crawlSetting,
        @Valid
        CrawlAuthSettingRequestDto crawlAuthSetting,
        @Valid
        List<CrawlEndpointRequestDto> crawlEndpoints
) implements SiteProfileRequestDto {

        @Override
        public CrawlSiteProfileCommand toSiteProfileCommand() {
                return new CrawlSiteProfileCommand(
                        crawlSetting.toCrawlSettingCommand(),
                        crawlAuthSetting.toCrawlAuthSettingCommand(),
                        toCrawlEndpointCommands()
                );
        }

        public List<CrawlEndpointCommand> toCrawlEndpointCommands(){
                return crawlEndpoints.stream()
                        .map(CrawlEndpointRequestDto::toCrawlEndpointCommand)
                        .toList();
        }

}
