package com.ryuqq.setof.support.external.core.sellic;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class SellicRequestInterceptor implements RequestInterceptor {

    @Value("${external-site.sellic.customer_id}")
    private String customerId;

    @Value("${external-site.sellic.api_key}")
    private String apiKey;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (requestTemplate.body() != null) {
            String body = new String(requestTemplate.body());
            String updatedBody = addAuthFieldsToBody(body, customerId, apiKey);
            requestTemplate.body(updatedBody);
        }
    }

    private String addAuthFieldsToBody(String body, String customerId, String apiKey) {
        return body.replaceFirst("\\{", String.format("{\"customer_id\":\"%s\",\"api_key\":\"%s\",", customerId, apiKey));
    }


}
