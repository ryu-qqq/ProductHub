package com.ryuqq.setof.support.external.core.sellic;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.support.external.core.ExternalMallNameContext;
import com.ryuqq.setof.support.external.core.ExternalSyncProductGroup;
import org.springframework.stereotype.Component;

@Component
public class SellicProductNameContextGenerator {


    public ExternalMallNameContext generateSellicProductDetail(String brandName, ExternalSyncProductGroup productGroup){
        String description = String.format("%s, %s, %s", brandName, productGroup.originName(), productGroup.styleCode());
        String name = productGroup.productGroupName();
        return new ExternalMallNameContext(name, description, productGroup.setOfProductGroupId(), productGroup.styleCode(), Origin.KR);
    }
}
