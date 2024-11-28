package com.ryuqq.setof.domain.core.color;

import com.ryuqq.setof.storage.db.core.color.ColorQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ColorFinder implements ColorQueryService{

    private final ColorQueryRepository colorQueryRepository;
    private final ColorMapper colorMapper;

    public ColorFinder(ColorQueryRepository colorQueryRepository, ColorMapper colorMapper) {
        this.colorQueryRepository = colorQueryRepository;
        this.colorMapper = colorMapper;
    }

    @Override
    public boolean existById(long colorId){
        return colorQueryRepository.existById(colorId);
    }

    @Override
    public List<Color> fetchColorByFilter(ColorFilter colorFilter){
        return colorQueryRepository.fetchColorByFilter(colorFilter.toStorageFilter())
                .stream()
                .map(colorMapper::toDomain)
                .toList();
    }

    @Override
    public long countByFilter(ColorFilter colorFilter){
        return colorQueryRepository.countByFilter(colorFilter.toStorageFilter());
    }


}
