package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.support.external.core.ExternalSyncProductGroup;
import com.ryuqq.setof.support.external.core.shein.domain.SheInProductNameContext;
import org.springframework.stereotype.Component;

@Component
public class SheInProductNameContextGenerator {

    public SheInProductNameContext generateBuyMaProductDetail(String brandName, ExternalSyncProductGroup productGroup) {
        String description = String.format("Brand Name: %s, Product Name: %s, Style Code: %s", brandName, productGroup.productGroupName(), productGroup.styleCode());
        String name = productGroup.productGroupName();

        if(containsKorean(description) || containsKorean(name)) {
            throw new IllegalArgumentException(String.format("Product Group Name Can't Contain Korean word %s, %s", name, description));
        }

        if(isEmpty(name) || isEmpty(description)) {
            throw new IllegalArgumentException(String.format("Product Group Name Can't Empty %s", name));
        }

        return new SheInProductNameContext(name, description, Origin.US);
    }

    private boolean containsKorean(String text) {
        return text.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*");
    }


    private boolean isEmpty(String text) {
        return text == null || text.isEmpty();
    }


}
