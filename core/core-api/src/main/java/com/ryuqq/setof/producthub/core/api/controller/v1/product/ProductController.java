package com.ryuqq.setof.producthub.core.api.controller.v1.product;

import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.product.ProductGroupCommandFacade;
import com.ryuqq.setof.domain.core.product.ProductGroupDocumentCommandFacade;
import com.ryuqq.setof.producthub.core.api.controller.support.ApiResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.request.ProductGroupCommandContextRequestDto;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.request.ProductGroupGetRequestDto;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.response.ProductGroupContextResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.response.ProductGroupInsertResponseDto;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.service.ProductGroupQueryFacade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ryuqq.setof.producthub.core.api.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ProductController {

    private final ProductGroupDocumentCommandFacade productGroupDocumentCommandFacade;
    private final ProductGroupQueryFacade productGroupQueryFacade;

    public ProductController(ProductGroupDocumentCommandFacade productGroupDocumentCommandFacade, ProductGroupQueryFacade productGroupQueryFacade) {
        this.productGroupDocumentCommandFacade = productGroupDocumentCommandFacade;
        this.productGroupQueryFacade = productGroupQueryFacade;
    }


    @PostMapping("/product/group")
    public ResponseEntity<ApiResponse<ProductGroupInsertResponseDto>> registerProductGroup(@RequestBody @Valid ProductGroupCommandContextRequestDto productGroupCommandContextRequestDto){
        long productGroupId = productGroupDocumentCommandFacade.insert(productGroupCommandContextRequestDto.toProductGroupCommandContext());
        return ResponseEntity.ok(ApiResponse.success(new ProductGroupInsertResponseDto(productGroupId)));
    }



    @PutMapping("/product/group/{productGroupId}")
    public ResponseEntity<ApiResponse<ProductGroupInsertResponseDto>> updateProductGroups(@PathVariable("productGroupId") long productGroupId, @RequestBody @Valid ProductGroupCommandContextRequestDto productGroupCommandContextRequestDto){
        productGroupDocumentCommandFacade.update(productGroupId, productGroupCommandContextRequestDto.toProductGroupCommandContext());
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
