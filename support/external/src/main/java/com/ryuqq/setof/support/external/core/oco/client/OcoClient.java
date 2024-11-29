package com.ryuqq.setof.support.external.core.oco.client;


import com.ryuqq.setof.support.external.core.oco.client.dto.OcoTokenRequestDto;
import com.ryuqq.setof.support.external.core.oco.client.dto.OcoTokenResponseDto;
import com.ryuqq.setof.support.external.core.oco.client.payload.OcoResponse;
import com.ryuqq.setof.support.external.core.oco.OcoConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ocoClient", url = "${external-site.oco.host-url}", configuration = OcoConfig.class)
public interface OcoClient {

    @PostMapping(value ="/auth/authentication.do")
    ResponseEntity<OcoResponse<OcoTokenResponseDto>> getAccessToken(
            @RequestBody OcoTokenRequestDto externalTokenRequest);

}
