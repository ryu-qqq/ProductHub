package com.ryuqq.setof.support.external.core.shein;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public class SheInRequestInterceptor implements RequestInterceptor {

    @Value("${external-site.sheIn.encoded_secret_key}")
    private String encodedSecretKey;

    @Value("${external-site.sheIn.secret_key}")
    private String secretKey;

    @Value("${external-site.sheIn.app-id}")
    private String appId;

    @Value("${external-site.sheIn.open-key-id}")
    private String openKeyId;

    private final SignatureGenerator signatureGenerator;

    public SheInRequestInterceptor(SignatureGenerator signatureGenerator) {
        this.signatureGenerator = signatureGenerator;
    }


    @Override
    public void apply(RequestTemplate requestTemplate) {

        String apiPath = requestTemplate.request().url();
        SheInHeader sheInHeader = generateSheInHeader(apiPath);
        requestTemplate.header("language", "en");
        requestTemplate.header("Content-Type", "application/json;charset=UTF-8");
        requestTemplate.header("x-lt-openKeyId", sheInHeader.openKeyId());
        requestTemplate.header("x-lt-timestamp", sheInHeader.timestamp());
        requestTemplate.header("x-lt-signature", sheInHeader.signature());
    }

    private SheInHeader generateSheInHeader(String apiPath) {
        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String randomKey = UUID.randomUUID().toString().substring(0, 5);
            String decryptedKey = signatureGenerator.decrypt(encodedSecretKey, secretKey);
            String signature = signatureGenerator.generateSignature(openKeyId, decryptedKey, apiPath, timestamp, randomKey);

            return new SheInHeader(openKeyId, timestamp, signature);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate SheIn header", e);
        }
    }


}
