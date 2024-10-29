package com.ryuqq.setof.domain.core.product.query;

import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.generic.mapper.AbstractGeneralSliceMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupContextSliceMapper extends AbstractGeneralSliceMapper<ProductGroupContext> {

    @Override
    public Slice<ProductGroupContext> toSlice(List<ProductGroupContext> data, int pageSize) {
        return super.toSlice(data, pageSize);
    }

    @Override
    public Slice<ProductGroupContext> toSlice(List<ProductGroupContext> data, int pageSize, long totalElements) {
        return super.toSlice(data, pageSize, totalElements);
    }

    @Override
    protected void setCursor(Slice<ProductGroupContext> slice) {
        if (!slice.isEmpty()) {
            List<ProductGroupContext> content = slice.getContent();
            ProductGroupContext t = content.getLast();
            slice.setCursor(t.getProductGroup().getProductGroupId());
        }
    }

}
