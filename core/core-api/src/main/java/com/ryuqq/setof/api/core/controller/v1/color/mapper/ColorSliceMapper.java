package com.ryuqq.setof.api.core.controller.v1.color.mapper;

import com.ryuqq.setof.api.core.controller.mapper.AbstractGeneralSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.color.response.ColorResponse;
import com.ryuqq.setof.domain.core.color.Color;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ColorSliceMapper extends AbstractGeneralSliceMapper<ColorResponse> {

    public Slice<ColorResponse> toSliceFromColors(List<Color> colors, int pageSize, long totalElements) {
        List<ColorResponse> responses = colors.stream()
                .map(ColorResponse::of)
                .toList();
        return toSlice(responses, pageSize, totalElements);
    }

    @Override
    protected void setCursor(Slice<ColorResponse> slice) {
        if (!slice.isEmpty()) {
            List<ColorResponse> content = slice.getContent();
            ColorResponse t = content.getLast();
            slice.setCursor(t.colorId());
        }
    }

}
