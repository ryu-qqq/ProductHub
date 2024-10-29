package com.ryuqq.setof.producthub.core.api.controller.v1.brand;

import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.producthub.core.api.controller.support.ApiResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.brand.request.BrandGetRequestDto;
import com.ryuqq.setof.producthub.core.api.controller.v1.brand.response.BrandResponse;
import com.ryuqq.setof.producthub.core.api.controller.v1.brand.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ryuqq.setof.producthub.core.api.controller.config.EndPointsConstants.BASE_END_POINT_V1;


@RequestMapping(BASE_END_POINT_V1)
@RestController
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    public ResponseEntity<ApiResponse<Slice<BrandResponse>>> getBrands(@ModelAttribute BrandGetRequestDto brandGetRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(brandService.getBrandSlices(brandGetRequestDto)));
    }

}
