package com.ryuqq.setof.producthub.core.api.controller.v1.color.mapper;

import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.producthub.core.api.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.setof.producthub.core.api.controller.v1.color.response.ColorResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ColorSliceMapper extends AbstractGeneralSliceMapper<ColorResponse> {

    @Override
    public Slice<ColorResponse> toSlice(List<ColorResponse> data, int pageSize) {
        return super.toSlice(data, pageSize);
    }

    @Override
    public Slice<ColorResponse> toSlice(List<ColorResponse> data, int pageSize, long totalElements) {
        return super.toSlice(data, pageSize, totalElements);
    }

    @Override
    protected void setCursor(Slice<ColorResponse> slice) {
        if (!slice.isEmpty()) {
            List<ColorResponse> content = slice.getContent();
            ColorResponse t = content.getLast();
            slice.setCursor(t.id());
        }
    }

}
