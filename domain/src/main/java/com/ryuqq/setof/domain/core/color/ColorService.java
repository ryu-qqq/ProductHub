package com.ryuqq.setof.domain.core.color;

import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {

    private final ColorFinder colorFinder;
    private final ColorSliceMapper colorSliceMapper;

    public ColorService(ColorFinder colorFinder, ColorSliceMapper colorSliceMapper) {
        this.colorFinder = colorFinder;
        this.colorSliceMapper = colorSliceMapper;
    }

    public Slice<Color> getColors(ColorFilter colorFilter){
        List<Color> colors = colorFinder.findColors(colorFilter);
        long colorCount = colorFinder.findColorCount(colorFilter);
        return colorSliceMapper.toSlice(colors, colorFilter.pageSize(), colorCount);
    }

}
