package com.ryuqq.setof.support.external.core.sellic.client;


import com.ryuqq.setof.support.external.core.sellic.SellicConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "sellicClient", url = "${external-site.sellic.host-url}", configuration = SellicConfig.class)
public interface SellicClient {
}
