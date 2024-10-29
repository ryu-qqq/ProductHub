package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.generic.mapper.AbstractGeneralSliceMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategorySliceMapper extends AbstractGeneralSliceMapper<Category> {
    @Override
    public Slice<Category> toSlice(List<Category> data, int pageSize) {
        return super.toSlice(data, pageSize);
    }

    @Override
    public Slice<Category> toSlice(List<Category> data, int pageSize, long totalElements) {
        return super.toSlice(data, pageSize, totalElements);
    }

    @Override
    protected void setCursor(Slice<Category> slice) {
        if (!slice.isEmpty()) {
            List<Category> content = slice.getContent();
            Category t = content.getLast();
            slice.setCursor(t.id());
        }
    }
}
