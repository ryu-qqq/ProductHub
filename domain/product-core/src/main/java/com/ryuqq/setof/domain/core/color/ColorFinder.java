package com.ryuqq.setof.domain.core.color;

import com.ryuqq.setof.storage.db.core.color.ColorQueryRepository;
import com.ryuqq.setof.storage.db.core.color.dto.ColorDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ColorFinder implements ColorQueryService{

    private final ColorQueryRepository colorQueryRepository;

    public ColorFinder(ColorQueryRepository colorQueryRepository) {
        this.colorQueryRepository = colorQueryRepository;
    }

    @Override
    public boolean colorExist(long colorId){
        return colorQueryRepository.fetchColorExists(colorId);
    }

    @Override
    public List<ColorRecord> findColors(ColorFilter colorFilter){
        List<ColorDto> colors = colorQueryRepository.fetchColors(colorFilter.toStorageFilter());
        return colors.stream()
                .map(ColorRecord::toColorRecord)
                .toList();
    }

    @Override
    public long findColorCount(ColorFilter colorFilter){
        return colorQueryRepository.fetchColorCount(colorFilter.toStorageFilter());
    }

}
