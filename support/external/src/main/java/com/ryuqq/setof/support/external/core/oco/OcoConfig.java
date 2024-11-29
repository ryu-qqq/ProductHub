package com.ryuqq.setof.support.external.core.oco;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OcoConfig {

    private final OcoAuthManager ocoAuthManager;

    public OcoConfig(OcoAuthManager ocoAuthManager) {
        this.ocoAuthManager = ocoAuthManager;
    }
//
//    @Bean
//    public OcoRequestInterceptor ocoRequestInterceptor() {
//        return new OcoRequestInterceptor(ocoAuthManager);
//    }
//
//    @Bean
//    public ErrorDecoder ocoErrorDecoder() {
//        return new OcoErrorDecoder(ocoAuthManager);
//    }

}
