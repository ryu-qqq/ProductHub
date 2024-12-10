package com.ryuqq.setof.support.external.core.sellic;


import com.ryuqq.setof.support.external.core.sellic.dto.SellicProductInsertRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "sellicClient", url = "${external-site.sellic.host-url}", configuration = SellicConfig.class)
public interface SellicClient {

    ResponseEntity<SellicResponse> insertProduct(@RequestBody  SellicProductInsertRequestDto insertRequestDto);
}
