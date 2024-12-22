package com.ryuqq.setof.api.core.controller.v1.site;

import com.ryuqq.setof.api.core.controller.support.ApiResponse;
import com.ryuqq.setof.api.core.controller.v1.site.request.ExternalProductGroupGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.site.response.ExternalProductGroupContextResponse;
import com.ryuqq.setof.api.core.controller.v1.site.service.ExternalProductServingService;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ryuqq.setof.api.core.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ExternalProductController {

    private final ExternalProductServingService externalProductServingService;

    public ExternalProductController(ExternalProductServingService externalProductServingService) {
        this.externalProductServingService = externalProductServingService;
    }

    @GetMapping("/external/site/product")
    public ResponseEntity<ApiResponse<Slice<ExternalProductGroupContextResponse>>> getExternalProducts(@ModelAttribute ExternalProductGroupGetRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponse.success(externalProductServingService.fetchExternalProductGroupByFilter(requestDto)));
    }

}
