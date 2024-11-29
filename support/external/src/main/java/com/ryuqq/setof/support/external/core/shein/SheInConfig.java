package com.ryuqq.setof.support.external.core.shein;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class SheInConfig {

    @Value("${external-site.sheIn.encoded_secret_key}")
    private String encodedSecretKey;

    @Value("${external-site.sheIn.secret_key}")
    private String secretKey;

    @Value("${external-site.sheIn.app-id}")
    private String appId;

    @Value("${external-site.sheIn.open-key-id}")
    private String openKeyId;

    private final SignatureGenerator signatureGenerator;

    public SheInConfig(SignatureGenerator signatureGenerator) {
        this.signatureGenerator = signatureGenerator;
    }


//    @Bean
//    public RequestInterceptor sheInRequestInterceptor() {
//        return requestTemplate -> {
//            String apiPath = requestTemplate.request().url();
//            SheInHeader sheInHeader = generateSheInHeader(apiPath);
//            requestTemplate.header("language", "en");
//            requestTemplate.header("Content-Type", "application/json;charset=UTF-8");
//            requestTemplate.header("x-lt-openKeyId", sheInHeader.openKeyId());
//            requestTemplate.header("x-lt-timestamp", sheInHeader.timestamp());
//            requestTemplate.header("x-lt-signature", sheInHeader.signature());
//        };
//    }
//
//    private SheInHeader generateSheInHeader(String apiPath) {
//        try {
//            String timestamp = String.valueOf(new Date().getTime());
//            String randomKey = generateRandomKey();
//            String decryptedKey = signatureGenerator.decrypt(encodedSecretKey, secretKey);
//            String signature = signatureGenerator.generateSignature(appId, decryptedKey, apiPath, timestamp, randomKey);
//
//            return new SheInHeader(openKeyId, timestamp, signature);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to generate SheIn header", e);
//        }
//    }
//
//    private String generateRandomKey() {
//        return java.util.UUID.randomUUID().toString().substring(0, 5);
//    }
//
//    @Bean
//    public ErrorDecoder sheInErrorDecoder() {
//        return new SheInErrorDecoder();
//    }


}
