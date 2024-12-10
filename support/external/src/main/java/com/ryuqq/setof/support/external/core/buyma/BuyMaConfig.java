package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.support.external.core.config.GlobalFeignConfig;
import org.springframework.context.annotation.Bean;

public class BuyMaConfig extends GlobalFeignConfig {

    @Bean
    public BuyMaRequestInterceptor buyMaRequestInterceptor() {
        return new BuyMaRequestInterceptor();
    }

}
