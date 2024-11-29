package com.ryuqq.setof.support.external.core.buyma;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuyMaConfig {

    @Value("${external-site.buyMa.accessToken}")
    private String accessToken;

    @Bean
    public RequestInterceptor buyMaRequestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Buyma-Personal-Shopper-Api-Access-Token", accessToken);
        };
    }

}
