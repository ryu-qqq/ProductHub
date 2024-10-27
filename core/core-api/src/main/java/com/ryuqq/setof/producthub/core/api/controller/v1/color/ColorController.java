package com.ryuqq.setof.producthub.core.api.controller.v1.color;

import com.ryuqq.setof.domain.core.color.Color;
import com.ryuqq.setof.domain.core.color.ColorService;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.producthub.core.api.controller.v1.color.request.ColorGetRequestDto;
import com.ryuqq.setof.producthub.core.api.controller.support.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ryuqq.setof.producthub.core.api.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("/colors")
    public ResponseEntity<ApiResponse<Slice<Color>>> getColors(@ModelAttribute ColorGetRequestDto colorGetRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(colorService.getColors(colorGetRequestDto.toColorFilter())));
    }


}
