package com.ryuqq.setof.api.core.controller.v1.color;

import com.ryuqq.setof.api.core.controller.support.ApiResponse;
import com.ryuqq.setof.api.core.controller.v1.color.request.ColorGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.color.response.ColorResponse;
import com.ryuqq.setof.api.core.controller.v1.color.service.ColorService;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ryuqq.setof.api.core.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("/colors")
    public ResponseEntity<ApiResponse<Slice<ColorResponse>>> getColors(@ModelAttribute ColorGetRequestDto colorGetRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(colorService.getColors(colorGetRequestDto)));
    }

}
