package com.ryuqq.setof.api.core.controller.v1.category.mapper;

import com.ryuqq.setof.api.core.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.category.response.CategoryResponse;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategorySliceMapper extends AbstractGeneralSliceMapper<CategoryResponse> {
    @Override
    public Slice<CategoryResponse> toSlice(List<CategoryResponse> data, int pageSize) {
        return super.toSlice(data, pageSize);
    }

    @Override
    public Slice<CategoryResponse> toSlice(List<CategoryResponse> data, int pageSize, long totalElements) {
        return super.toSlice(data, pageSize, totalElements);
    }

    @Override
    protected void setCursor(Slice<CategoryResponse> slice) {
        if (!slice.isEmpty()) {
            List<CategoryResponse> content = slice.getContent();
            CategoryResponse t = content.getLast();
            slice.setCursor(t.categoryId());
        }
    }
}
