package com.ryuqq.setof.support.external.core;

import org.springframework.stereotype.Component;

@Component
public class ExternalProductRequestBuilder {

    public ExternalMallRegistrationRequest buildRequest(long productGroupId, long siteId, ExternalMallProductPayload product) {
        return new ExternalMallRegistrationRequest(productGroupId, siteId, product);
    }

}
