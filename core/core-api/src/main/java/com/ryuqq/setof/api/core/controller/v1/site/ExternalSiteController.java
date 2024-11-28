package com.ryuqq.setof.api.core.controller.v1.site;

import com.ryuqq.setof.api.core.controller.support.ApiResponse;
import com.ryuqq.setof.api.core.controller.v1.site.request.SellerSiteRelationRequestDto;
import com.ryuqq.setof.api.core.controller.v1.site.temp.PartnerAdminProductRequestDto;
import com.ryuqq.setof.domain.core.site.external.ExternalProductSyncCommandService;
import com.ryuqq.setof.domain.core.site.external.ExternalProductSyncService;
import com.ryuqq.setof.domain.core.site.external.ExternalSiteSellerRelationCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ryuqq.setof.api.core.controller.config.EndPointsConstants.BASE_END_POINT_V1;


@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ExternalSiteController {

    private final ExternalSiteSellerRelationCommandService externalSiteSellerRelationCommandService;
    private final ExternalProductSyncCommandService externalProductSyncCommandService;

    private final ExternalProductSyncService externalProductSyncService;

    public ExternalSiteController(ExternalSiteSellerRelationCommandService externalSiteSellerRelationCommandService, ExternalProductSyncCommandService externalProductSyncCommandService, ExternalProductSyncService externalProductSyncService) {
        this.externalSiteSellerRelationCommandService = externalSiteSellerRelationCommandService;
        this.externalProductSyncCommandService = externalProductSyncCommandService;
        this.externalProductSyncService = externalProductSyncService;
    }

    @PostMapping("/site/sync-seller")
    public ResponseEntity<ApiResponse<Integer>> registerSellerSiteRelations(@RequestBody SellerSiteRelationRequestDto sellerSiteRelationRequestDto){
        return ResponseEntity.ok(ApiResponse.success(externalSiteSellerRelationCommandService.inserts(sellerSiteRelationRequestDto.toSellerSiteRelationCommand())));
    }

    @PostMapping("/site/sync-seller-product")
    public ResponseEntity<ApiResponse<Long>> getSellerSiteRelations(@RequestBody SellerSiteRelationRequestDto sellerSiteRelationRequestDto){
        return ResponseEntity.ok(ApiResponse.success(externalProductSyncCommandService.syncExternalProducts(sellerSiteRelationRequestDto.toSellerSiteRelationCommand())));
    }


    @PostMapping("/site/sync-external-product")
    public void syncProductToExternalSite(@RequestBody PartnerAdminProductRequestDto partnerAdminProductRequestDto){
        externalProductSyncService.temp(partnerAdminProductRequestDto.siteId(), partnerAdminProductRequestDto.sellerId());
        //externalProductSyncService.read();
        //return ResponseEntity.ok(ApiResponse.success(externalProductSyncCommandService.syncExternalProducts(sellerSiteRelationRequestDto.toSellerSiteRelationCommand())));
    }


}
