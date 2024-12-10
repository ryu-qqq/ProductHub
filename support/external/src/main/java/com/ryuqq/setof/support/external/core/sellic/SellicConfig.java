package com.ryuqq.setof.support.external.core.sellic;

import com.ryuqq.setof.support.external.core.config.GlobalFeignConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class SellicConfig extends GlobalFeignConfig {

    @Bean
    public SellicRequestInterceptor sellicRequestInterceptor() {
        return new SellicRequestInterceptor();
    }

}
