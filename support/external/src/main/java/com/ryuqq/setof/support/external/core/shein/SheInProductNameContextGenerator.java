package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.support.external.core.ExternalMallNameContext;
import com.ryuqq.setof.support.external.core.ExternalSyncProductGroup;
import org.springframework.stereotype.Component;

@Component
public class SheInProductNameContextGenerator {

    public ExternalMallNameContext generateBuyMaProductDetail(String brandName, ExternalSyncProductGroup productGroup) {
        String description = String.format("Brand Name: %s, Product Name: %s, Style Code: %s", brandName, productGroup.productGroupName(), productGroup.styleCode());
        String name = productGroup.productGroupName();

        if(containsKorean(description) || containsKorean(name)) {
            throw new IllegalArgumentException(String.format("Product Group Name Can't Contain Korean word %s, %s", name, description));
        }

        if(isEmpty(name) || isEmpty(description)) {
            throw new IllegalArgumentException(String.format("Product Group Name Can't Empty %s", name));
        }

        return new ExternalMallNameContext(name, description, productGroup.setOfProductGroupId(), productGroup.styleCode(), Origin.US);
    }

    private boolean containsKorean(String text) {
        return text.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*");
    }


    private boolean isEmpty(String text) {
        return text == null || text.isEmpty();
    }


}