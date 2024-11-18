package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.CrawlEndpointCommand;
import com.ryuqq.setof.domain.core.site.CrawlTaskCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CrawlEndpointRequestDto(

        @NotBlank(message = "End Point Url cannot be blank.")
        @Size(max = 255, message = "End Point Url must be 255 characters or less.")
        String endPointUrl,
        @Size(max = 255, message = "parameters must be 255 characters or less.")
        String parameters,
        @Valid
        List<CrawlTaskRequestDto> crawlTasks
) {

        public CrawlEndpointCommand toCrawlEndpointCommand(){
                return new CrawlEndpointCommand(endPointUrl, parameters, toCrawlTaskCommands());
        }

        public List<CrawlTaskCommand> toCrawlTaskCommands(){
                return crawlTasks.stream()
                        .map(CrawlTaskRequestDto::toCrawlTaskCommand)
                        .toList();
        }
}
