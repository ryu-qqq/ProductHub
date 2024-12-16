package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.support.external.core.config.GlobalFeignConfig;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class SheInConfig extends GlobalFeignConfig {

    private final SignatureGenerator signatureGenerator;

    public SheInConfig(SignatureGenerator signatureGenerator) {
        this.signatureGenerator = signatureGenerator;
    }

    @Bean
    public SheInRequestInterceptor sheInRequestInterceptor() {
        return new SheInRequestInterceptor(signatureGenerator);
    }

    @Bean
    public ErrorDecoder sheInErrorDecoder() {
        return new SheInErrorDecoder();
    }

}
