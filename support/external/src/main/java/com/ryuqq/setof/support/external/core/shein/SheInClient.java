package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.support.external.core.shein.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "sheInClient", url = "${external-site.sheIn.host-url}", configuration = SheInConfig.class)
public interface SheInClient {

    @PostMapping("/open-api/goods/query-category-tree")
    ResponseEntity<SheInResponse<List<SheInCategoryResponse>>> fetchCategories();

    @PostMapping("/open-api/goods/query-attribute-template")
    ResponseEntity<SheInResponse<List<SheInAttributeResponse>>> fetchAttributes(@RequestBody SheInAttributeRequestDto sheInAttributeRequestDto);

    @PostMapping("/open-api/goods/transform-pic")
    ResponseEntity<SheInResponse<SheInImageUploadResponse>> uploadImages(@RequestBody SheInImageUploadRequestDto sheInImageUploadRequestDto);

    @PostMapping("/open-api/goods/product/publishOrEdit")
    ResponseEntity<SheInResponse<SheInProductInsertResponseDto>> insertProduct(@RequestBody SheInProductInsertRequestDto requestDto);

}
