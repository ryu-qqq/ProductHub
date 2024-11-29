package com.ryuqq.setof.domain.core.color;

import com.ryuqq.setof.storage.db.core.color.dto.ColorDto;
import org.springframework.stereotype.Component;

@Component
public class ColorMapper {

    public Color toDomain(ColorDto colorDto) {
        return new Color(
                colorDto.getId(),
                colorDto.getColorName()
        );
    }

}
