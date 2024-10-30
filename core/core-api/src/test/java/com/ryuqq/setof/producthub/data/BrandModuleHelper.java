package com.ryuqq.setof.producthub.data;

import com.ryuqq.setof.domain.core.brand.BrandRecord;
import com.ryuqq.setof.producthub.core.api.controller.v1.brand.response.BrandResponse;

public class BrandModuleHelper {

    public static BrandRecord toBrandRecord(){
        return new BrandRecord(1L, "BrandX", "https://brandx.com/logo.png", true);
    }

    public static BrandResponse toBrandResponse(){
        return new BrandResponse(1L, "BrandX", "https://brandx.com/logo.png", true);
    }


}
