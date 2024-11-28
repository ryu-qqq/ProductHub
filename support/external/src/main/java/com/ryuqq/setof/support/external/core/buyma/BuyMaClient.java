package com.ryuqq.setof.support.external.core.buyma;


import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaProductContext;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaVariant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "buyMaClient", url = "${external-site.buyMa.host-url}", configuration = BuyMaConfig.class)
public interface BuyMaClient {

    @PostMapping("/api/v1/products.json")
    Map<String, Object> sendProduct(@RequestBody BuyMaProductContext product);

    @PostMapping("/api/v1/products/variants.json")
    Map<String, Object> updateProduct(@RequestBody BuyMaVariant variant);

}
