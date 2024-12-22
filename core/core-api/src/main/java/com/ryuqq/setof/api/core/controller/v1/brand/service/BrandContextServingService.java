package com.ryuqq.setof.api.core.controller.v1.brand.service;

import com.ryuqq.setof.api.core.controller.v1.brand.mapper.BrandSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.brand.request.BrandGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.brand.response.BrandResponse;
import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.domain.core.brand.BrandQueryService;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandContextServingService {
    private final BrandSliceMapper brandSliceMapper;
    private final BrandQueryService brandQueryService;

    public BrandContextServingService(BrandSliceMapper brandSliceMapper, BrandQueryService brandQueryService) {
        this.brandSliceMapper = brandSliceMapper;
        this.brandQueryService = brandQueryService;
    }

    public Slice<BrandResponse> fetchBrands(BrandGetRequestDto brandGetRequestDto) {
        List<Brand> brands = brandQueryService.fetchBrandsByFilter(brandGetRequestDto.toBrandFilter());
        long brandCount = brandQueryService.countByFilter(brandGetRequestDto.toBrandFilter());
        return brandSliceMapper.toSliceFromBrands(brands, brandGetRequestDto.pageSize(), brandCount);
    }

}
