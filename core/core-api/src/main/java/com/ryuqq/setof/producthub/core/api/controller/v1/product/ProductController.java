package com.ryuqq.setof.producthub.core.api.controller.v1.product;

import com.ryuqq.setof.domain.core.product.command.ProductGroupCommandResponse;
import com.ryuqq.setof.domain.core.product.command.ProductGroupRegistrationService;
import com.ryuqq.setof.producthub.core.api.controller.support.ApiResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.request.ProductGroupInsertRequestDto;
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

    private final ProductGroupRegistrationService productGroupCommandService;

    public ProductController(ProductGroupRegistrationService productGroupCommandService) {
        this.productGroupCommandService = productGroupCommandService;
    }

    @PostMapping("/product/group")
    public ResponseEntity<ApiResponse<ProductGroupInsertResponseDto>> registerProduct(@RequestBody ProductGroupInsertRequestDto productGroupInsertRequestDto){
        ProductGroupCommandResponse response = productGroupCommandService.insert(productGroupInsertRequestDto.toProductGroupCommandContext());
        ProductGroupInsertResponseDto productGroupInsertResponseDto = new ProductGroupInsertResponseDto(response.productGroupId(), response.productIds());
        return ResponseEntity.ok(ApiResponse.success(productGroupInsertResponseDto));
    }



}
