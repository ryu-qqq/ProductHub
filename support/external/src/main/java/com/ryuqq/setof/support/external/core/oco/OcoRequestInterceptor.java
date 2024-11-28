package com.ryuqq.setof.support.external.core.oco;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class OcoRequestInterceptor implements RequestInterceptor {

    @Value("${external-site.oco.api-key}")
    private String apiKey;

    private final OcoAuthManager ocoAuthManager;

    public OcoRequestInterceptor(OcoAuthManager ocoAuthManager) {
        this.ocoAuthManager = ocoAuthManager;
    }

    @Override
    public void apply(RequestTemplate template) {
        //String token = ocoAuthManager.getToken();
//        template.header("token", token);
//        template.header("ApiKey", apiKey);
    }
}