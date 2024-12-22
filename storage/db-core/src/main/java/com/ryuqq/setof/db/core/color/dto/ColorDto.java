package com.ryuqq.setof.storage.db.core.color.dto;

import com.querydsl.core.annotations.QueryProjection;

public class ColorDto {
    private long id;
    private String colorName;

    @QueryProjection
    public ColorDto(long id, String colorName) {
        this.id = id;
        this.colorName = colorName;
    }

    public long getId() {
        return id;
    }

    public String getColorName() {
        return colorName;
    }

}
