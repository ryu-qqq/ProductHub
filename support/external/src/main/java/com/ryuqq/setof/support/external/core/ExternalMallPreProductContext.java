package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.OptionType;
import com.ryuqq.setof.enums.core.SiteName;

import java.util.List;

public record ExternalMallPreProductContext(
        long siteId,
        SiteName siteName,
        ExternalSyncBrand brand,
        ExternalSyncCategory category,
        ExternalSyncProductGroup productGroup,
        List<ExternalSyncProduct> products,
        List<ExternalSyncCategoryOption> externalCategoryOptions,
        ExternalSyncOptionResult gptOptionsResult,
        List<ExternalSyncStandardSize> standardSizes
) {

    public String getExternalProductGroupId(){
        return productGroup.externalProductGroupId();
    }

    public long getSetOfProductGroupId(){
        return productGroup.setOfProductGroupId();
    }

    public long getProductGroupId(){
        return productGroup.productGroupId();
    }

    public String getStyleCode(){
        return productGroup.styleCode();
    }

    public OptionType getOptionType(){
        return productGroup.optionType();
    }


    public DetailedSyncResult createDetailedSyncResult() {
        return new DetailedSyncResult(productGroup.productGroupId(), siteId);
    }

}
