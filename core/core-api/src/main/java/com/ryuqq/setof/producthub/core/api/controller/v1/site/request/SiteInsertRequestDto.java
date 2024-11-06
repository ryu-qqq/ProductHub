package com.ryuqq.setof.producthub.core.api.controller.v1.site.request;

import com.ryuqq.setof.core.Origin;
import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.domain.core.site.SiteCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SiteInsertRequestDto(

        @NotBlank(message = "Site Name cannot be blank.")
        @Size(max = 100, message = "Site Name must be 100 characters or less.")
        String name,

        @NotBlank(message = "Base Url cannot be blank.")
        @Size(max = 255, message = "Base Url must be 255 characters or less.")
        String baseUrl,

        @NotNull(message = "Country Code cannot be null.")
        Origin countryCode,

        @NotNull(message = "Site Type cannot be null.")
        SiteType siteType
) {

        public SiteCommand toSiteCommand(){
                return new SiteCommand(
                        name,
                        baseUrl,
                        countryCode,
                        siteType
                );
        }
}
