package com.ryuqq.setof.api.core.controller.v1.product;

import com.ryuqq.setof.api.core.controller.support.ApiResponse;
import com.ryuqq.setof.api.core.controller.v1.product.request.*;
import com.ryuqq.setof.api.core.controller.v1.product.response.ProductGroupContextResponse;
import com.ryuqq.setof.api.core.controller.v1.product.response.ProductGroupInsertResponseDto;
import com.ryuqq.setof.api.core.controller.v1.product.response.GptTrainingDataResponse;
import com.ryuqq.setof.api.core.controller.v1.product.service.BatchResultCommandService;
import com.ryuqq.setof.api.core.controller.v1.product.service.GptTrainingDataServingService;
import com.ryuqq.setof.api.core.controller.v1.product.service.ProductGroupContextServingService;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.product.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ryuqq.setof.api.core.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ProductController {

    private final ProductGroupContextCommandService productGroupContextCommandService;
    private final ProductGroupCommandService productGroupCommandService;
    private final ProductGroupContextServingService productGroupContextServingService;
    private final GptTrainingDataServingService gptTrainingDataServingService;
    private final BatchResultCommandService batchResultCommandService;

    public ProductController(ProductGroupCommandService productGroupCommandService, ProductGroupContextCommandService productGroupContextCommandService, ProductGroupContextServingService productGroupContextServingService, GptTrainingDataServingService gptTrainingDataServingService, BatchResultCommandService batchResultCommandService) {
        this.productGroupCommandService = productGroupCommandService;
        this.productGroupContextCommandService = productGroupContextCommandService;
        this.productGroupContextServingService = productGroupContextServingService;
        this.gptTrainingDataServingService = gptTrainingDataServingService;
        this.batchResultCommandService = batchResultCommandService;
    }

    @PostMapping("/product/group")
    public ResponseEntity<ApiResponse<ProductGroupInsertResponseDto>> registerProductGroup(@RequestBody @Valid ProductGroupCommandContextRequestDto productGroupCommandContextRequestDto){
        long productGroupId = productGroupContextCommandService.insert(productGroupCommandContextRequestDto.toProductGroupCommandContext());
        return ResponseEntity.ok(ApiResponse.success(new ProductGroupInsertResponseDto(productGroupId)));
    }

    @PutMapping("/product/group/{productGroupId}")
    public ResponseEntity<ApiResponse<ProductGroupInsertResponseDto>> updateProductGroups(@PathVariable("productGroupId") long productGroupId, @RequestBody @Valid ProductGroupCommandContextRequestDto productGroupCommandContextRequestDto){
        productGroupContextCommandService.update(productGroupId, productGroupCommandContextRequestDto.toProductGroupCommandContext());
        return ResponseEntity.ok(ApiResponse.success(new ProductGroupInsertResponseDto(productGroupId)));
    }

    @GetMapping("/product/group")
    public ResponseEntity<ApiResponse<Slice<ProductGroupContextResponse>>> getProductGroups(@ModelAttribute ProductGroupGetRequestDto productGroupGetRequestDto){
        return ResponseEntity.ok(ApiResponse.success(productGroupContextServingService.fetchProductGroupContextsByFilter(productGroupGetRequestDto)));
    }

    @GetMapping("/product/group/{productGroupId}")
    public ResponseEntity<ApiResponse<ProductGroupContextResponse>> getProductGroup(@PathVariable("productGroupId") long productGroupId){
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/product/processing-waiting")
    public ResponseEntity<ApiResponse<Slice<GptTrainingDataResponse>>> getProductGroupsForGptTraining(@ModelAttribute ProductGroupGetRequestDto productGroupGetRequestDto){
        return ResponseEntity.ok(ApiResponse.success(gptTrainingDataServingService.getProductGroupsForGptTraining(productGroupGetRequestDto)));
    }

    @PostMapping("/product/processing-completed")
    public ResponseEntity<ApiResponse<Integer>> getProductGroupForProcessingData(@RequestBody GptBatchSaveRequest requestDto){
        batchResultCommandService.batchResultIntegration(requestDto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PatchMapping("/product/status")
    public ResponseEntity<ApiResponse<Integer>> updateProductStatus(@RequestBody ProductStatusUpdateRequestDto requestDto){
        productGroupCommandService.updateStatus(requestDto.productGroupIds(), requestDto.productStatus());
        return ResponseEntity.ok(ApiResponse.success(requestDto.productGroupIds().size()));
    }

}
