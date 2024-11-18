package com.ryuqq.setof.api.core.controller.v1.product.mapper;


import com.ryuqq.setof.api.core.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.product.response.ProductGroupContextResponse;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupContextSliceMapper extends AbstractGeneralSliceMapper<ProductGroupContextResponse> {

    @Override
    public Slice<ProductGroupContextResponse> toSlice(List<ProductGroupContextResponse> data, int pageSize) {
        return super.toSlice(data, pageSize);
    }

    @Override
    public Slice<ProductGroupContextResponse> toSlice(List<ProductGroupContextResponse> data, int pageSize, long totalElements) {
        return super.toSlice(data, pageSize, totalElements);
    }

    @Override
    protected void setCursor(Slice<ProductGroupContextResponse> slice) {
        if (!slice.isEmpty()) {
            List<ProductGroupContextResponse> content = slice.getContent();
            ProductGroupContextResponse t = content.getLast();
            slice.setCursor(t.productGroup().productGroupId());
        }
    }

}
