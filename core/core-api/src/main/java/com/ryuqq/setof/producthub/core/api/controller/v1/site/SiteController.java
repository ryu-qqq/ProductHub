package com.ryuqq.setof.producthub.core.api.controller.v1.site;

import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.domain.core.site.command.SiteContextCommandFacade;
import com.ryuqq.setof.producthub.core.api.controller.support.ApiResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.request.SiteInsertRequestDto;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.response.SiteContextResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.response.SiteInsertResponseDto;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.service.SiteQueryFacade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ryuqq.setof.producthub.core.api.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class SiteController {

    private final SiteQueryFacade siteQueryFacade;
    private final SiteContextCommandFacade siteContextCommandFacade;

    public SiteController(SiteQueryFacade siteQueryFacade, SiteContextCommandFacade siteContextCommandFacade) {
        this.siteQueryFacade = siteQueryFacade;
        this.siteContextCommandFacade = siteContextCommandFacade;
    }

    @GetMapping("/site/{siteId}")
    public ResponseEntity<ApiResponse<SiteContextResponse>> getSites(@PathVariable("siteId") long siteId){
        return ResponseEntity.ok(ApiResponse.success(siteQueryFacade.getSiteContextResponse(siteId)));
    }

    @PostMapping("/site")
    public ResponseEntity<ApiResponse<SiteInsertResponseDto>> registerSite(@RequestBody @Valid SiteInsertRequestDto siteInsertRequestDto){
        long siteId = siteContextCommandFacade.insert(siteInsertRequestDto.toSiteCommand());
        return ResponseEntity.ok(ApiResponse.success(new SiteInsertResponseDto(siteId)));
    }

}
