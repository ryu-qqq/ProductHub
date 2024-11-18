package com.ryuqq.setof.api.core.data;

import com.ryuqq.setof.api.core.controller.v1.brand.response.BrandResponse;
import com.ryuqq.setof.domain.core.brand.BrandRecord;

public class BrandModuleHelper {

    public static BrandRecord toBrandRecord(){
        return new BrandRecord(1L, "BrandX", "https://brandx.com/logo.png", true);
    }

    public static BrandResponse toBrandResponse(){
        return new BrandResponse(1L, "BrandX", "https://brandx.com/logo.png", true);
    }


}
