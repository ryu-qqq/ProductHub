package com.ryuqq.setof.support.external.core.shein.domain;

import com.ryuqq.setof.support.external.core.ExternalMallImage;
import com.ryuqq.setof.support.external.core.ExternalMallImageContext;

import java.util.List;

public record SheInImageGroupContext(
        String imageGroupCode,
        List<SheInImageContext> sheInImageContexts
) implements ExternalMallImageContext {

    @Override
    public List<? extends ExternalMallImage> getImages() {
        return sheInImageContexts;
    }

}
