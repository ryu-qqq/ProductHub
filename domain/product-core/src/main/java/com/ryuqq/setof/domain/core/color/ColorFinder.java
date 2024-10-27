package com.ryuqq.setof.domain.core.color;

import com.ryuqq.setof.storage.db.core.color.ColorQueryRepository;
import com.ryuqq.setof.storage.db.core.color.dto.ColorDto;
import org.springframework.stereotype.Component;

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
        List<ColorDto> colorDtos = colorQueryRepository.fetchColors(colorFilter.toStorageFilter());
        return toColors(colorDtos);
    }

    public long findColorCount(ColorFilter colorFilter){
        return colorQueryRepository.fetchColorCount(colorFilter.toStorageFilter());
    }

    private List<Color> toColors(List<ColorDto> colorDtos){
        return colorDtos.stream()
                .map(c ->
                        new Color(c.getId(), c.getColorName())
                    )
                .toList();
    }


}
