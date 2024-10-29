package com.ryuqq.setof.producthub.core.api.controller.v1.brand.mapper;

import com.ryuqq.setof.domain.core.brand.BrandRecord;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.producthub.core.api.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.setof.producthub.core.api.controller.v1.brand.response.BrandResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandSliceMapper extends AbstractGeneralSliceMapper<BrandResponse> {

    @Override
    public Slice<BrandResponse> toSlice(List<BrandResponse> data, int pageSize, long totalElements) {
        return super.toSlice(data, pageSize, totalElements);
    }

    @Override
    public Slice<BrandResponse> toSlice(List<BrandResponse> data, int pageSize) {
        return super.toSlice(data, pageSize);
    }

    @Override
    protected void setCursor(Slice<BrandResponse> slice) {
        if (!slice.isEmpty()) {
            List<BrandResponse> content = slice.getContent();
            BrandResponse t = content.getLast();
            slice.setCursor(t.id());
        }
    }



}
