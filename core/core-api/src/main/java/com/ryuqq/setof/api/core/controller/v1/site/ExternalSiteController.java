package com.ryuqq.setof.api.core.controller.v1.site;

import com.ryuqq.setof.api.core.controller.support.ApiResponse;
import com.ryuqq.setof.api.core.controller.v1.site.request.ExternalProductGroupGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.site.request.ExternalProductIntegrationRequestDto;
import com.ryuqq.setof.api.core.controller.v1.site.request.SellerSiteRelationRequestDto;
import com.ryuqq.setof.api.core.controller.v1.site.response.ExternalProductGroupContextResponse;
import com.ryuqq.setof.api.core.controller.v1.site.service.ExternalProductIntegrationService;
import com.ryuqq.setof.api.core.controller.v1.site.service.ExternalProductServingService;
import com.ryuqq.setof.domain.core.category.MappingCategoryCommandService;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.site.external.ExternalProductGroupSyncCommandService;
import com.ryuqq.setof.domain.core.site.external.ExternalSiteSellerRelationCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ryuqq.setof.api.core.controller.config.EndPointsConstants.BASE_END_POINT_V1;


@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ExternalSiteController {

    private final ExternalSiteSellerRelationCommandService externalSiteSellerRelationCommandService;
    private final ExternalProductGroupSyncCommandService externalProductGroupSyncCommandService;
    private final ExternalProductIntegrationService externalProductIntegrationService;
    private final ExternalProductServingService externalProductServingService;
    private final MappingCategoryCommandService mappingCategoryCommandService;

    public ExternalSiteController(ExternalSiteSellerRelationCommandService externalSiteSellerRelationCommandService, ExternalProductGroupSyncCommandService externalProductGroupSyncCommandService, ExternalProductIntegrationService externalProductIntegrationService, ExternalProductServingService externalProductServingService, MappingCategoryCommandService mappingCategoryCommandService) {
        this.externalSiteSellerRelationCommandService = externalSiteSellerRelationCommandService;
        this.externalProductGroupSyncCommandService = externalProductGroupSyncCommandService;
        this.externalProductIntegrationService = externalProductIntegrationService;
        this.externalProductServingService = externalProductServingService;
        this.mappingCategoryCommandService = mappingCategoryCommandService;
    }

    @PostMapping("/site/sync-seller")
    public ResponseEntity<ApiResponse<Integer>> registerSellerSiteRelations(@RequestBody SellerSiteRelationRequestDto sellerSiteRelationRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(externalSiteSellerRelationCommandService.inserts(sellerSiteRelationRequestDto.toSellerSiteRelationCommand())));
    }

    @PostMapping("/site/sync-seller-product")
    public ResponseEntity<ApiResponse<Long>> getSellerSiteRelations(@RequestBody SellerSiteRelationRequestDto sellerSiteRelationRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(externalProductGroupSyncCommandService.syncExternalProducts(sellerSiteRelationRequestDto.toSellerSiteRelationCommand())));
    }

    @PostMapping("/site/sync-external-product")
    public ResponseEntity<ApiResponse<Integer>> syncProductToExternalSite(@RequestBody ExternalProductIntegrationRequestDto externalProductIntegrationRequestDto){
        return ResponseEntity.ok(ApiResponse.success(externalProductIntegrationService.integration(externalProductIntegrationRequestDto)));
    }

    @GetMapping("/site/external-product")
    public ResponseEntity<ApiResponse<Slice<ExternalProductGroupContextResponse>>> getExternalProducts(@ModelAttribute ExternalProductGroupGetRequestDto requestDto){
        return ResponseEntity.ok(ApiResponse.success(externalProductServingService.fetchExternalProductGroupByFilter(requestDto)));
    }

    @PostMapping("/sites/{siteId}/categories/mapping")
    public ResponseEntity<ApiResponse<Integer>> insertMappingCategories(@PathVariable("siteId") long siteId){
        return ResponseEntity.ok(ApiResponse.success(mappingCategoryCommandService.saveAll(siteId)));
    }

    @PostMapping("/sites/{siteId}/categories/option/mapping")
    public ResponseEntity<ApiResponse<Integer>> insertMappingCategoryOptions(@PathVariable("siteId") long siteId){
        return ResponseEntity.ok(ApiResponse.success(mappingCategoryCommandService.saveAllMappingCategoryOptions(siteId)));
    }


}
