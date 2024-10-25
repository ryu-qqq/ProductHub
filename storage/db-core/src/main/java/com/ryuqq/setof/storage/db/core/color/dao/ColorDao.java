package com.ryuqq.setof.storage.db.core.color.dao;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.domain.core.color.Color;

public class ColorDao {
    private long id;
    private String colorName;

    @QueryProjection
    public ColorDao(long id, String colorName) {
        this.id = id;
        this.colorName = colorName;
    }

    public long getId() {
        return id;
    }

    public String getColorName() {
        return colorName;
    }

    public Color toColor(){
        return new Color(id, colorName);
    }

}
