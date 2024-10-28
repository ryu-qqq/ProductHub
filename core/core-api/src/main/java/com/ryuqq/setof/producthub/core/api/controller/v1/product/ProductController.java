package com.ryuqq.setof.producthub.core.api.controller.v1.product;

import com.ryuqq.setof.domain.core.product.command.ProductGroupInsertService;
import com.ryuqq.setof.producthub.core.api.controller.support.ApiResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.request.ProductGroupCommandContextRequestDto;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.response.ProductGroupInsertResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ryuqq.setof.producthub.core.api.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ProductController {

    private final ProductGroupInsertService productGroupCommandService;

    public ProductController(ProductGroupInsertService productGroupCommandService) {
        this.productGroupCommandService = productGroupCommandService;
    }

    @PostMapping("/product/group")
    public ResponseEntity<ApiResponse<ProductGroupInsertResponseDto>> registerProduct(@RequestBody ProductGroupCommandContextRequestDto productGroupCommandContextRequestDto){
        long productGroupId = productGroupCommandService.insert(productGroupCommandContextRequestDto.toProductGroupCommandContext());
        return ResponseEntity.ok(ApiResponse.success(new ProductGroupInsertResponseDto(productGroupId)));
    }

}
