package com.ryuqq.setof.api.core.controller.v1.brand;

import com.ryuqq.setof.api.core.controller.support.ApiResponse;
import com.ryuqq.setof.api.core.controller.v1.brand.request.BrandGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.brand.response.BrandResponse;
import com.ryuqq.setof.api.core.controller.v1.brand.service.BrandContextServingService;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ryuqq.setof.api.core.controller.config.EndPointsConstants.BASE_END_POINT_V1;


@RequestMapping(BASE_END_POINT_V1)
@RestController
public class BrandController {

    private final BrandContextServingService brandContextServingService;

    public BrandController(BrandContextServingService brandContextServingService) {
        this.brandContextServingService = brandContextServingService;
    }

    @GetMapping("/brands")
    public ResponseEntity<ApiResponse<Slice<BrandResponse>>> getBrands(@ModelAttribute BrandGetRequestDto brandGetRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(brandContextServingService.fetchBrands(brandGetRequestDto)));
    }

}
