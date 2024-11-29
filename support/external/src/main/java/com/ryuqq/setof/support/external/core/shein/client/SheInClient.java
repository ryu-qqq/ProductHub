package com.ryuqq.setof.support.external.core.shein.client;

import com.ryuqq.setof.support.external.core.shein.SheInConfig;
import com.ryuqq.setof.support.external.core.shein.client.dto.SheInCategoryResponse;
import com.ryuqq.setof.support.external.core.shein.client.payload.SheInResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "sheInClient", url = "${external-site.sheIn.host-url}", configuration = SheInConfig.class)
public interface SheInClient {

    @PostMapping("/open-api/goods/query-category-tree")
    Object getCategories();

}
