package com.ryuqq.setof.support.external.core.buyma;


import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaProductInsertRequestDto;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaStockUpdateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "buyMaClient", url = "${external-site.buyMa.host-url}", configuration = BuyMaConfig.class)
public interface BuyMaClient {

    @PostMapping("/api/v1/products.json")
    ResponseEntity<Object> insertProduct(@RequestBody BuyMaProductInsertRequestDto product);

    @PostMapping("/api/v1/products/variants.json")
    ResponseEntity<BuyMaResponse<?>> updateProduct(@RequestBody BuyMaStockUpdateRequestDto variant);

}
