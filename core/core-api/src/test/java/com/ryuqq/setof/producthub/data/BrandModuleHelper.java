package com.ryuqq.setof.producthub.data;

import com.ryuqq.setof.domain.core.brand.BrandRecord;

public class BrandModuleHelper {

    public static BrandRecord toBrandRecord(){
        return new BrandRecord(1L, "BrandX", "https://brandx.com/logo.png", true);

    }
}
