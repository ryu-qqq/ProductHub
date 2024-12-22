package com.ryuqq.setof.support.external.core.oco;

import com.ryuqq.setof.support.external.core.oco.dto.OcoTokenRequestDto;
import com.ryuqq.setof.support.external.core.oco.dto.OcoTokenResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Component
public class OcoAuthManager {

    private final RestTemplate restTemplate;

    @Value("${external-site.oco.host-url}")
    private String hostUrl;
    @Value("${external-site.oco.id}")
    private String id;
    @Value("${external-site.oco.password}")
    private String password;

    @Value("${external-site.oco.api-key}")
    private String apiKey;


    private volatile String token;
    private volatile Instant expiryTime;

    public OcoAuthManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public synchronized String getToken() {
        if (token == null || isTokenExpired()) {
            fetchToken();
        }
        return token;
    }

    private boolean isTokenExpired() {
        return expiryTime == null || Instant.now().isAfter(expiryTime);
    }

    private synchronized void fetchToken() {
        String url = hostUrl + "/auth/authentication.do";
        OcoTokenRequestDto request = new OcoTokenRequestDto(id, password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ApiKey", apiKey);


        HttpEntity<OcoTokenRequestDto> httpEntity = new HttpEntity<>(request, headers);


        ParameterizedTypeReference<OcoResponse<OcoTokenResponseDto>> responseType =
                new ParameterizedTypeReference<>() {};


        ResponseEntity<OcoResponse<OcoTokenResponseDto>> responseEntity =
                restTemplate.exchange(url, HttpMethod.POST, httpEntity, responseType);


        OcoResponse<OcoTokenResponseDto> response = responseEntity.getBody();

        if (response != null && response.apiResult() != null) {
            this.token = response.apiResult().token();
            this.expiryTime = Instant.now().plusSeconds(36000);
        } else {
            throw new RuntimeException("Failed to fetch token from OCO");
        }
    }

    public synchronized void refreshToken() {
        this.token = null;
        fetchToken();
    }

}