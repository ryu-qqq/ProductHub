package com.ryuqq.setof.producthub.core.api.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.command.CrawlEndpointCommand;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CrawlEndpointRequestDto(

        @NotBlank(message = "End Point Url cannot be blank.")
        @Size(max = 255, message = "End Point Url must be 255 characters or less.")
        String endPointUrl,

        @Min(value = 1, message = "Crawl Frequency must be 1 or more.")
        int crawlFrequency
) {

        public CrawlEndpointCommand toCrawlEndpointCommand(){
                return new CrawlEndpointCommand(endPointUrl, crawlFrequency);
        }
}
