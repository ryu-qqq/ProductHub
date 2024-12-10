package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.SizeOrigin;

public class StandardSizeDto {

    private Long categoryId;
    private Long regionId;
    private SizeOrigin name;
    private String sizeValue;

    @QueryProjection
    public StandardSizeDto(Long categoryId, Long regionId, SizeOrigin name, String sizeValue) {
        this.categoryId = categoryId;
        this.regionId = regionId;
        this.name = name;
        this.sizeValue = sizeValue;
    }


    public Long getCategoryId() {
        return categoryId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public SizeOrigin getName() {
        return name;
    }

    public String getSizeValue() {
        return sizeValue;
    }




}
