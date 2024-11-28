package com.ryuqq.setof.api.core.controller.v1.color.service;

import com.ryuqq.setof.api.core.controller.v1.color.mapper.ColorSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.color.request.ColorGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.color.response.ColorResponse;
import com.ryuqq.setof.domain.core.color.Color;
import com.ryuqq.setof.domain.core.color.ColorQueryService;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {

    private final ColorQueryService colorQueryService;
    private final ColorSliceMapper colorSliceMapper;

    public ColorService(ColorQueryService colorQueryService, ColorSliceMapper colorSliceMapper) {
        this.colorQueryService = colorQueryService;
        this.colorSliceMapper = colorSliceMapper;
    }

    public Slice<ColorResponse> getColors(ColorGetRequestDto colorGetRequestDto){
        List<Color> colors = colorQueryService.fetchColorByFilter(colorGetRequestDto.toColorFilter());
        long colorCount = colorQueryService.countByFilter(colorGetRequestDto.toColorFilter());
        return colorSliceMapper.toSliceFromColors(colors, colorGetRequestDto.pageSize(), colorCount);
    }

}
