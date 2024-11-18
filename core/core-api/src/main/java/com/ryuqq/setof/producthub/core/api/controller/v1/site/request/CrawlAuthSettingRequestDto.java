package com.ryuqq.setof.producthub.core.api.controller.v1.site.request;

import com.ryuqq.setof.enums.core.AuthType;
import com.ryuqq.setof.domain.core.site.CrawlAuthSettingCommand;
import jakarta.validation.constraints.NotNull;

public record CrawlAuthSettingRequestDto(
        @NotNull(message = "Auth Type cannot be null.")
        AuthType authType,
        String authEndpoint,
        String authHeaders,
        String authPayload
) {
        public CrawlAuthSettingCommand toCrawlAuthSettingCommand(){
                return new CrawlAuthSettingCommand(
                        authType, authEndpoint, authHeaders, authPayload
                );
        }

}
