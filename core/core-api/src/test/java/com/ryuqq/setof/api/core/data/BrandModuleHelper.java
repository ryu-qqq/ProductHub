package com.ryuqq.setof.api.core.data;

import com.ryuqq.setof.api.core.controller.v1.brand.response.BrandResponse;
import com.ryuqq.setof.domain.core.brand.Brand;

public class BrandModuleHelper {

    public static Brand toBrandRecord(){
        return new Brand(1L, "BrandX", "", "https://brandx.com/logo.png", true);
    }

    public static BrandResponse toBrandResponse(){
        return new BrandResponse(1L, "BrandX","", "https://brandx.com/logo.png", true);
    }


}
