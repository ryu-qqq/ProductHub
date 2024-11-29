package com.ryuqq.setof.support.external.core;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalProductImageCommandGenerator {

    public List<ExternalMallImageResult> generateImageCommands(long productGroupId, ExternalMallProductPayload product) {

        return product.getExternalMallImagePayload().stream()
                .map(i -> new ExternalMallImageResult(
                        productGroupId,
                        "",
                        i.getDisplayOrder(),
                        i.getOriginUrl(),
                        i.getImageUrl()
                ))
                .toList();
    }
}
