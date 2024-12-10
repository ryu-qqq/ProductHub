package com.ryuqq.setof.support.external.core.buyma;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class BuyMaRequestInterceptor implements RequestInterceptor {

    @Value("${external-site.buyMa.accessToken}")
    private String accessToken;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("X-Buyma-Personal-Shopper-Api-Access-Token", accessToken);

    }
}
