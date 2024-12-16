package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SizeOrigin;

public record ExternalSyncStandardSize(
        Long categoryId,
        Long regionId,
        SizeOrigin name,
        String sizeValue
) {

    public String getRegionSize(){
        return name.name()+sizeValue;
    }
}
