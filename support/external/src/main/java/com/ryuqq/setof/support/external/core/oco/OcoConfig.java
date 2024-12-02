package com.ryuqq.setof.support.external.core.oco;

import com.ryuqq.setof.support.external.core.config.GlobalFeignConfig;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class OcoConfig extends GlobalFeignConfig {

    private final OcoAuthManager ocoAuthManager;

    public OcoConfig(OcoAuthManager ocoAuthManager) {
        this.ocoAuthManager = ocoAuthManager;
    }

    @Bean
    public OcoRequestInterceptor ocoRequestInterceptor() {
        return new OcoRequestInterceptor(ocoAuthManager);
    }

    @Bean
    public ErrorDecoder ocoErrorDecoder() {
        return new OcoErrorDecoder(ocoAuthManager);
    }

}
