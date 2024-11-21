package com.ryuqq.setof.api.core.controller.v1.batch;


import com.ryuqq.setof.api.core.controller.support.ApiResponse;
import com.ryuqq.setof.batch.core.product.ProductGroupBatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ryuqq.setof.api.core.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ProductGroupBatchController {

    private final ProductGroupBatchService productGroupBatchService;

    public ProductGroupBatchController(ProductGroupBatchService productGroupBatchService) {
        this.productGroupBatchService = productGroupBatchService;
    }

    @PostMapping("/product-insert-trigger")
    public ResponseEntity<ApiResponse<String>> triggerProductInsertBatchJob() {
        return ResponseEntity.ok(ApiResponse.success(productGroupBatchService.executeProductGroupInsertJob()));
    }


}
