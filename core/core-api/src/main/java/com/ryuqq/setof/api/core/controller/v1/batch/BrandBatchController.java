package com.ryuqq.setof.api.core.controller.v1.batch;

import com.ryuqq.setof.api.core.controller.support.ApiResponse;
import com.ryuqq.setof.batch.core.brand.BrandIndexingBatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ryuqq.setof.api.core.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class BrandBatchController {

    private final BrandIndexingBatchService brandIndexingBatchService;

    public BrandBatchController(BrandIndexingBatchService brandIndexingBatchService) {
        this.brandIndexingBatchService = brandIndexingBatchService;
    }

    @PostMapping("/brand-indexing-trigger")
    public ResponseEntity<ApiResponse<String>> triggerBrandIndexingJob() {
        return ResponseEntity.ok(ApiResponse.success(brandIndexingBatchService.executeBrandIndexingJob()));
    }

}
