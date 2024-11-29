package com.ryuqq.setof.api.core.controller.v1.site;

import com.ryuqq.setof.api.core.controller.support.ApiResponse;
import com.ryuqq.setof.api.core.controller.v1.site.request.SiteGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.site.request.SiteInsertRequestDto;
import com.ryuqq.setof.api.core.controller.v1.site.request.SiteProfileRequestDto;
import com.ryuqq.setof.api.core.controller.v1.site.response.SiteContextResponse;
import com.ryuqq.setof.api.core.controller.v1.site.response.SiteInsertResponseDto;
import com.ryuqq.setof.api.core.controller.v1.site.response.SiteResponse;
import com.ryuqq.setof.api.core.controller.v1.site.service.SiteService;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.site.SiteContextCommandFacade;
import com.ryuqq.setof.domain.core.site.SiteProfileCommandFacade;
import com.ryuqq.setof.enums.core.SiteType;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ryuqq.setof.api.core.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class SiteController {

    private final SiteService siteService;
    private final SiteContextCommandFacade siteContextCommandFacade;
    private final SiteProfileCommandFacade siteProfileCommandFacade;


    public SiteController(SiteService siteService, SiteContextCommandFacade siteContextCommandFacade, SiteProfileCommandFacade siteProfileCommandFacade) {
        this.siteService = siteService;
        this.siteContextCommandFacade = siteContextCommandFacade;
        this.siteProfileCommandFacade = siteProfileCommandFacade;
    }

    @GetMapping("/site")
    public ResponseEntity<ApiResponse<Slice<SiteResponse>>> getSites(@ModelAttribute SiteGetRequestDto siteGetRequestDto){
        return ResponseEntity.ok(ApiResponse.success(siteService.getSiteResponses(siteGetRequestDto.toSiteFilter())));
    }

    @GetMapping("/site/{siteId}")
    public ResponseEntity<ApiResponse<SiteContextResponse>> getSite(@PathVariable("siteId") long siteId){
        return ResponseEntity.ok(ApiResponse.success(siteService.getSiteContextResponse(siteId)));
    }

    @PostMapping("/site")
    public ResponseEntity<ApiResponse<SiteInsertResponseDto>> registerSite(@RequestBody @Valid SiteInsertRequestDto siteInsertRequestDto){
        long siteId = siteContextCommandFacade.insert(siteInsertRequestDto.toSiteCommand());
        return ResponseEntity.ok(ApiResponse.success(new SiteInsertResponseDto(siteId)));
    }

    @PostMapping("/site/{siteType}/{siteId}")
    public ResponseEntity<ApiResponse<SiteInsertResponseDto>> registerSiteProfile(
            @PathVariable("siteType") SiteType siteType,
            @PathVariable("siteId") long siteId,
            @RequestBody @Valid SiteProfileRequestDto siteProfileRequestDto){
        siteProfileCommandFacade.insert(siteType, siteId, siteProfileRequestDto.toSiteProfileCommand());
        return ResponseEntity.ok(ApiResponse.success(new SiteInsertResponseDto(siteId)));
    }

    @PutMapping("/site/{siteType}/{siteId}/profile/{mappingId}")
    public ResponseEntity<ApiResponse<SiteInsertResponseDto>> updateSiteProfile(
            @PathVariable("siteType") SiteType siteType,
            @PathVariable("siteId") long siteId,
            @PathVariable("mappingId") long mappingId,
            @RequestBody @Valid SiteProfileRequestDto siteProfileRequestDto){
        siteProfileCommandFacade.update(siteType, siteId, mappingId, siteProfileRequestDto.toSiteProfileCommand());
        return ResponseEntity.ok(ApiResponse.success(new SiteInsertResponseDto(siteId)));
    }
}
