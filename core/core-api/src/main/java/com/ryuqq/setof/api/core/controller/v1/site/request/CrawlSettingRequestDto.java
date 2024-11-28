package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.crawl.CrawlSettingCommand;
import com.ryuqq.setof.enums.core.CrawlType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CrawlSettingRequestDto(
        @Min(value = 1, message = "Crawl Frequency must be 1 or more.")
        int crawlFrequency,

        @NotNull(message = "Crawl Type cannot be null.")
        CrawlType crawlType
) {
    public CrawlSettingCommand toCrawlSettingCommand(){
        return new CrawlSettingCommand(crawlFrequency, crawlType);
    }
}
