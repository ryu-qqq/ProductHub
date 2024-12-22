package com.ryuqq.setof.api.core.controller.v1.site;

import com.ryuqq.setof.api.core.controller.support.ApiResponse;
import com.ryuqq.setof.api.core.controller.v1.site.request.CrawlProductGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.site.request.CrawlProductInsertRequestDto;
import com.ryuqq.setof.api.core.controller.v1.site.response.CrawlProductResponse;
import com.ryuqq.setof.api.core.controller.v1.site.response.SiteInsertResponseDto;
import com.ryuqq.setof.api.core.controller.v1.site.service.CrawlProductServingService;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.site.crawl.CrawlProductCommand;
import com.ryuqq.setof.domain.core.site.crawl.CrawlProductCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ryuqq.setof.api.core.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class CrawlProductController {

    private final CrawlProductCommandService crawlProductCommandService;
    private final CrawlProductServingService crawlProductServingService;

    public CrawlProductController(CrawlProductCommandService crawlProductCommandService, CrawlProductServingService crawlProductServingService) {
        this.crawlProductCommandService = crawlProductCommandService;
        this.crawlProductServingService = crawlProductServingService;
    }

    @PostMapping("/site/crawl/product")
    public ResponseEntity<ApiResponse<SiteInsertResponseDto>> registerCrawlProducts(
            @RequestBody List<CrawlProductInsertRequestDto> crawlProductInsertRequests){
        List<CrawlProductCommand> crawlProductCommands = crawlProductInsertRequests.stream().map(CrawlProductInsertRequestDto::toCrawlProductCommand).toList();
        crawlProductCommandService.saveAll(crawlProductCommands);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/site/crawl/product")
    public ResponseEntity<ApiResponse<Slice<CrawlProductResponse>>> getCrawlProducts(
            @ModelAttribute CrawlProductGetRequestDto crawlProductGetRequestDto){
        return ResponseEntity.ok(ApiResponse.success(crawlProductServingService.fetchByFilter(crawlProductGetRequestDto.toCrawlProductFilter())));
    }


}
