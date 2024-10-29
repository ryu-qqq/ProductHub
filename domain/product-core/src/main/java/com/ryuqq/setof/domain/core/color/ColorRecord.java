package com.ryuqq.setof.domain.core.color;

import com.ryuqq.setof.storage.db.core.color.dto.ColorDto;

public record ColorRecord(
        long id,
        String colorName
){
    public static ColorRecord toColorRecord(ColorDto color) {
        return new ColorRecord(
                color.getId(),
                color.getColorName()
        );
    }

}
