package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandFinder brandFinder;
    private final BrandSliceMapper brandSliceMapper;

    public BrandService(BrandFinder brandFinder, BrandSliceMapper brandSliceMapper) {
        this.brandFinder = brandFinder;
        this.brandSliceMapper = brandSliceMapper;
    }

    public Slice<Brand> getBrands(BrandFilter brandFilter){
        List<Brand> brands = brandFinder.findBrands(brandFilter);
        long brandCount = brandFinder.findBrandCount(brandFilter);
        return brandSliceMapper.toSlice(brands, brandFilter.pageSize(), brandCount);
    }

}
