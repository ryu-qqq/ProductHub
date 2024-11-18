package com.ryuqq.setof.api.core.data;


import com.ryuqq.setof.api.core.controller.v1.color.response.ColorResponse;

public class ColorModuleHelper {

    public static ColorResponse toColorResponse(){
        return new ColorResponse(1L, "Blue");
    }

}
