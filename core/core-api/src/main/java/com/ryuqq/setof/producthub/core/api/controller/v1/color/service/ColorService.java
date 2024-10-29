package com.ryuqq.setof.producthub.core.api.controller.v1.color.service;

import com.ryuqq.setof.domain.core.color.ColorQueryService;
import com.ryuqq.setof.domain.core.color.ColorRecord;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.producthub.core.api.controller.v1.color.mapper.ColorSliceMapper;
import com.ryuqq.setof.producthub.core.api.controller.v1.color.request.ColorGetRequestDto;
import com.ryuqq.setof.producthub.core.api.controller.v1.color.response.ColorResponse;
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
        List<ColorRecord> colors = colorQueryService.findColors(colorGetRequestDto.toColorFilter());
        long colorCount = colorQueryService.findColorCount(colorGetRequestDto.toColorFilter());
        return colorSliceMapper.toSlice(toColorResponses(colors), colorGetRequestDto.pageSize(), colorCount);
    }

    private List<ColorResponse> toColorResponses(List<ColorRecord> colors){
        return colors.stream().map(ColorResponse::of)
                .toList();
    }

}
