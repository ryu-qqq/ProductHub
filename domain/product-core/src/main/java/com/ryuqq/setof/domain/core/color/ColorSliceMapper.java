package com.ryuqq.setof.domain.core.color;

import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.generic.mapper.AbstractGeneralSliceMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ColorSliceMapper extends AbstractGeneralSliceMapper<Color> {
    @Override
    public Slice<Color> toSlice(List<Color> data, int pageSize) {
        return super.toSlice(data, pageSize);
    }

    @Override
    public Slice<Color> toSlice(List<Color> data, int pageSize, long totalElements) {
        return super.toSlice(data, pageSize, totalElements);
    }

    @Override
    protected void setCursor(Slice<Color> slice) {
        if (!slice.isEmpty()) {
            List<Color> content = slice.getContent();
            Color t = content.getLast();
            slice.setCursor(t.id());
        }
    }

}
