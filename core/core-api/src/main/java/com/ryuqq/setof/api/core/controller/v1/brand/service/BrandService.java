package com.ryuqq.setof.api.core.controller.v1.brand.service;

import com.ryuqq.setof.api.core.controller.v1.brand.mapper.BrandSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.brand.request.BrandGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.brand.response.BrandResponse;
import com.ryuqq.setof.domain.core.brand.BrandDocumentFinder;
import com.ryuqq.setof.domain.core.brand.BrandQueryService;
import com.ryuqq.setof.domain.core.brand.BrandRecord;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    private final BrandSliceMapper brandSliceMapper;
    private final BrandQueryService brandQueryService;

    public BrandService(BrandSliceMapper brandSliceMapper, BrandDocumentFinder brandQueryService) {
        this.brandSliceMapper = brandSliceMapper;
        this.brandQueryService = brandQueryService;
    }

    public Slice<BrandResponse> getBrands(BrandGetRequestDto brandGetRequestDto){
        List<BrandRecord> brands = brandQueryService.findBrands(brandGetRequestDto.toBrandFilter());
        long brandCount = brandQueryService.findBrandCount(brandGetRequestDto.toBrandFilter());
        return brandSliceMapper.toSlice(toBrandRecords(brands), brandGetRequestDto.getPageSize(), brandCount);
    }

    private List<BrandResponse> toBrandRecords(List<BrandRecord> brandRecords){
        return brandRecords.stream().map(BrandResponse::of)
                .toList();
    }

}
