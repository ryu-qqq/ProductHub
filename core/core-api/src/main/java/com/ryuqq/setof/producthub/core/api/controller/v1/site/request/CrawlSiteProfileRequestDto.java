package com.ryuqq.setof.producthub.core.api.controller.v1.site.request;

import com.ryuqq.setof.core.CrawlType;
import com.ryuqq.setof.domain.core.site.command.CrawlEndpointCommand;
import com.ryuqq.setof.domain.core.site.command.CrawlSettingCommand;
import com.ryuqq.setof.domain.core.site.command.CrawlSiteProfileCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CrawlSiteProfileRequestDto(

        @Min(value = 1, message = "Crawl Frequency must be 1 or more.")
        int crawlFrequency,

        @NotNull(message = "Crawl Type cannot be null.")
        CrawlType crawlType,

        @Valid
        CrawlAuthSettingRequestDto crawlAuthSetting,

        @Valid
        List<CrawlEndpointRequestDto> crawlEndpoints
) implements SiteProfileRequestDto {

        @Override
        public CrawlSiteProfileCommand toSiteProfileCommand() {
                return new CrawlSiteProfileCommand(
                        toCrawlSettingCommand(),
                        crawlAuthSetting.toCrawlAuthSettingCommand(),
                        toCrawlEndpointCommands()
                );
        }

        public CrawlSettingCommand toCrawlSettingCommand(){
                return new CrawlSettingCommand(crawlFrequency, crawlType);
        }

        public List<CrawlEndpointCommand> toCrawlEndpointCommands(){
                return crawlEndpoints.stream()
                        .map(CrawlEndpointRequestDto::toCrawlEndpointCommand)
                        .toList();
        }

}
