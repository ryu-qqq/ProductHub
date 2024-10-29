package com.ryuqq.setof.producthub.core.api.controller.v1.product;

import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.product.command.ProductGroupInsertFacade;
import com.ryuqq.setof.domain.core.product.query.ProductGroupContext;
import com.ryuqq.setof.domain.core.product.query.ProductGroupDomainQueryFacade;
import com.ryuqq.setof.producthub.core.api.controller.support.ApiResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.request.ProductGroupCommandContextRequestDto;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.request.ProductGroupGetRequestDto;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.response.ProductGroupInsertResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ryuqq.setof.producthub.core.api.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ProductController {

    private final ProductGroupInsertFacade productGroupCommandService;
    private final ProductGroupDomainQueryFacade productGroupDomainQueryFacade;

    public ProductController(ProductGroupInsertFacade productGroupCommandService, ProductGroupDomainQueryFacade productGroupDomainQueryFacade) {
        this.productGroupCommandService = productGroupCommandService;
        this.productGroupDomainQueryFacade = productGroupDomainQueryFacade;
    }

    @PostMapping("/product/group")
    public ResponseEntity<ApiResponse<ProductGroupInsertResponseDto>> registerProductGroup(@RequestBody ProductGroupCommandContextRequestDto productGroupCommandContextRequestDto){
        long productGroupId = productGroupCommandService.insert(productGroupCommandContextRequestDto.toProductGroupCommandContext());
        return ResponseEntity.ok(ApiResponse.success(new ProductGroupInsertResponseDto(productGroupId)));
    }

    @GetMapping("/product/group")
    public ResponseEntity<ApiResponse<Slice<ProductGroupContext>>> getProductGroups(@ModelAttribute ProductGroupGetRequestDto productGroupGetRequestDto){
        return ResponseEntity.ok(ApiResponse.success(productGroupDomainQueryFacade.getProductGroupContexts(productGroupGetRequestDto.toProductGroupFilter())));
    }


}
