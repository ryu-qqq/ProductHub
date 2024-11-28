package com.ryuqq.setof.api.core.controller.v1.brand.mapper;

import com.ryuqq.setof.api.core.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.brand.response.BrandResponse;
import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandSliceMapper extends AbstractGeneralSliceMapper<BrandResponse> {

    public Slice<BrandResponse> toSliceFromBrands(List<Brand> brands, int pageSize, long totalElements) {
        List<BrandResponse> responses = brands.stream()
                .map(BrandResponse::of)
                .toList();
        return toSlice(responses, pageSize, totalElements);
    }

    @Override
    protected void setCursor(Slice<BrandResponse> slice) {
        if (!slice.isEmpty()) {
            List<BrandResponse> content = slice.getContent();
            BrandResponse t = content.getLast();
            slice.setCursor(t.brandId());
        }
    }



}
