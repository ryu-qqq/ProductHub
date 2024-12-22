package com.ryuqq.setof.support.external.core.oco;


import com.ryuqq.setof.support.external.core.oco.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ocoClient", url = "${external-site.oco.host-url}", configuration = OcoConfig.class)
public interface OcoClient {

    @PostMapping(value ="/auth/authentication.do")
    ResponseEntity<OcoResponse<OcoTokenResponseDto>> getAccessToken(
            @RequestBody OcoTokenRequestDto externalTokenRequest);

    @PostMapping(value ="/product/add.do")
    ResponseEntity<OcoResponse<?>> insertProduct(@RequestBody OcoProductInsertRequestDto requestDto);

    @PostMapping(value ="/product/update.do")
    ResponseEntity<OcoResponse<?>> updateProduct(@RequestBody OcoProductUpdateRequestDto requestDto);
}
