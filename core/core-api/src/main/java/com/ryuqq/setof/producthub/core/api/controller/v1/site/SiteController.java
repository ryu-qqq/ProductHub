package com.ryuqq.setof.producthub.core.api.controller.v1.site;

import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.site.*;
import com.ryuqq.setof.producthub.core.api.controller.support.ApiResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.request.*;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.response.CrawlProductResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.response.SiteContextResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.response.SiteInsertResponseDto;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.response.SiteResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.service.CrawlProductQueryFacade;
import com.ryuqq.setof.producthub.core.api.controller.v1.site.service.SiteQueryFacade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ryuqq.setof.producthub.core.api.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class SiteController {

    private final SiteQueryFacade siteQueryFacade;
    private final SiteContextCommandFacade siteContextCommandFacade;
    private final SiteProfileCommandFacade siteProfileCommandFacade;
    private final CrawlProductCommandService crawlProductCommandService;
    private final CrawlProductQueryFacade crawlProductQueryFacade;

    public SiteController(SiteQueryFacade siteQueryFacade, SiteContextCommandFacade siteContextCommandFacade, SiteProfileCommandFacade siteProfileCommandFacade, CrawlProductCommandService crawlProductCommandService, CrawlProductQueryFacade crawlProductQueryFacade) {
        this.siteQueryFacade = siteQueryFacade;
        this.siteContextCommandFacade = siteContextCommandFacade;
        this.siteProfileCommandFacade = siteProfileCommandFacade;
        this.crawlProductCommandService = crawlProductCommandService;
        this.crawlProductQueryFacade = crawlProductQueryFacade;
    }

    @GetMapping("/site")
    public ResponseEntity<ApiResponse<Slice<SiteResponse>>> getSites(@ModelAttribute SiteGetRequestDto siteGetRequestDto){
        return ResponseEntity.ok(ApiResponse.success(siteQueryFacade.getSiteResponses(siteGetRequestDto.toSiteFilter())));
    }

    @GetMapping("/site/{siteId}")
    public ResponseEntity<ApiResponse<SiteContextResponse>> getSite(@PathVariable("siteId") long siteId){
        return ResponseEntity.ok(ApiResponse.success(siteQueryFacade.getSiteContextResponse(siteId)));
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

    @PostMapping("/site/crawl/product")
    public ResponseEntity<ApiResponse<SiteInsertResponseDto>> registerCrawlProducts(
            @RequestBody List<CrawlProductInsertRequestDto> crawlProductInsertRequests){
        List<CrawlProductCommand> crawlProductCommands = crawlProductInsertRequests.stream().map(CrawlProductInsertRequestDto::toCrawlProductCommand).toList();
        crawlProductCommandService.inserts(crawlProductCommands);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/site/crawl/product")
    public ResponseEntity<ApiResponse<Slice<CrawlProductResponse>>> getCrawlProducts(
            @ModelAttribute CrawlProductGetRequestDto crawlProductGetRequestDto){
        return ResponseEntity.ok(ApiResponse.success(crawlProductQueryFacade.getCrawlProducts(crawlProductGetRequestDto.toCrawlProductFilter())));
    }


}
