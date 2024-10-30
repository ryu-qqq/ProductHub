package com.ryuqq.setof.producthub.data;

import com.ryuqq.setof.producthub.core.api.controller.v1.color.response.ColorResponse;

public class ColorModuleHelper {

    public static ColorResponse toColorResponse(){
        return new ColorResponse(1L, "Blue");
    }

}
