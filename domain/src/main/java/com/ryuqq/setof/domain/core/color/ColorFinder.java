package com.ryuqq.setof.domain.core.color;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class ColorFinder {

    private final ColorQueryRepository colorQueryRepository;

    public ColorFinder(ColorQueryRepository colorQueryRepository) {
        this.colorQueryRepository = colorQueryRepository;
    }

    public boolean colorExist(long colorId){
        return colorQueryRepository.fetchColorExists(colorId);
    }

    public List<Color> findColors(ColorFilter colorFilter){
        return colorQueryRepository.fetchColors(colorFilter);
    }

    public long findColorCount(ColorFilter colorFilter){
        return colorQueryRepository.fetchColorCount(colorFilter);
    }


}
