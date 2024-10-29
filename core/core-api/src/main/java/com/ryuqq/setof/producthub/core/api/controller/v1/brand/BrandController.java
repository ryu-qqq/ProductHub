package com.ryuqq.setof.producthub.core.api.controller.v1.brand;

import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.domain.core.brand.BrandDomainQueryService;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.producthub.core.api.controller.v1.brand.request.BrandGetRequestDto;
import com.ryuqq.setof.producthub.core.api.controller.support.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ryuqq.setof.producthub.core.api.controller.config.EndPointsConstants.BASE_END_POINT_V1;


@RequestMapping(BASE_END_POINT_V1)
@RestController
public class BrandController {

    private final BrandDomainQueryService brandDomainQueryService;

    public BrandController(BrandDomainQueryService brandDomainQueryService) {
        this.brandDomainQueryService = brandDomainQueryService;
    }

    @GetMapping("/brands")
    public ResponseEntity<ApiResponse<Slice<Brand>>> getBrands(@ModelAttribute BrandGetRequestDto brandGetRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(brandDomainQueryService.getBrands(brandGetRequestDto.toBrandFilter())));
    }

}
