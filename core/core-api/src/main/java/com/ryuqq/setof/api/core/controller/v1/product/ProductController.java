package com.ryuqq.setof.api.core.controller.v1.product;

import com.ryuqq.setof.api.core.controller.support.ApiResponse;
import com.ryuqq.setof.api.core.controller.v1.product.request.ProductGroupCommandContextRequestDto;
import com.ryuqq.setof.api.core.controller.v1.product.request.ProductGroupGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.product.response.ProductGroupContextResponse;
import com.ryuqq.setof.api.core.controller.v1.product.response.ProductGroupInsertResponseDto;
import com.ryuqq.setof.api.core.controller.v1.product.service.ProductGroupQueryFacade;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.product.ProductGroupContextCommandService;
import com.ryuqq.setof.domain.core.product.ProductGroupContextCommandFacade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ryuqq.setof.api.core.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ProductController {

    private final ProductGroupContextCommandService productGroupContextCommandService;
    private final ProductGroupQueryFacade productGroupQueryFacade;

    public ProductController(ProductGroupContextCommandFacade productGroupDocumentCommandFacade, ProductGroupQueryFacade productGroupQueryFacade) {
        this.productGroupContextCommandService = productGroupDocumentCommandFacade;
        this.productGroupQueryFacade = productGroupQueryFacade;
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
        return ResponseEntity.ok(ApiResponse.success(productGroupQueryFacade.getProductGroupContexts(productGroupGetRequestDto.toProductGroupFilter())));
    }

    @GetMapping("/product/group/{productGroupId}")
    public ResponseEntity<ApiResponse<ProductGroupContextResponse>> getProductGroup(@PathVariable("productGroupId") long productGroupId){
        return ResponseEntity.ok(ApiResponse.success(null));
    }


}
