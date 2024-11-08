package com.ryuqq.setof.producthub.core.api.controller.v1.site.request;

import com.ryuqq.setof.core.ActionType;
import com.ryuqq.setof.core.ProcessType;
import com.ryuqq.setof.domain.core.site.CrawlTaskCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CrawlTaskRequestDto(
        @Positive(message = "Step order must be a positive integer.")
        int stepOrder,
        @NotNull(message = "Task Type cannot be null. Use None for Nothing to do")
        ProcessType type,
        String target,
        @NotNull(message = "Action Type cannot be null. Use None for Nothing to do")
        ActionType action,
        @NotBlank(message = "Params cannot be empty. Use '{}' for empty JSON.")
        String params,
        @NotBlank(message = "Response mapping cannot be empty. Use '{}' for empty JSON.")
        String responseMapping
) {

    public CrawlTaskCommand toCrawlTaskCommand(){
        return new CrawlTaskCommand(
                stepOrder,
                type,
                target,
                action,
                params,
                responseMapping
        );
    }

}
