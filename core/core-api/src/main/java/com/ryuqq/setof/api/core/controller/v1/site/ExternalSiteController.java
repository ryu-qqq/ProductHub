package com.ryuqq.setof.api.core.controller.v1.site;

import com.ryuqq.setof.api.core.controller.support.ApiResponse;
import com.ryuqq.setof.api.core.controller.v1.site.request.SellerSiteRelationRequestDto;
import com.ryuqq.setof.domain.core.site.SellerSiteRelationCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ryuqq.setof.api.core.controller.config.EndPointsConstants.BASE_END_POINT_V1;


@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ExternalSiteController {

    private final SellerSiteRelationCommandService sellerSiteRelationCommandService;

    public ExternalSiteController(SellerSiteRelationCommandService sellerSiteRelationCommandService) {
        this.sellerSiteRelationCommandService = sellerSiteRelationCommandService;
    }

    @PostMapping("/site/sync-seller")
    public ResponseEntity<ApiResponse<Integer>> registerSellerSiteRelations(@RequestBody SellerSiteRelationRequestDto sellerSiteRelationRequestDto){
        return ResponseEntity.ok(ApiResponse.success(sellerSiteRelationCommandService.inserts(sellerSiteRelationRequestDto.toSellerSiteRelationCommand())));
    }


}
