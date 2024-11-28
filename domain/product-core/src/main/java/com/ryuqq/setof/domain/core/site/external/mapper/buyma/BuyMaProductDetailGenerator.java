package com.ryuqq.setof.domain.core.site.external.mapper.buyma;

import com.ryuqq.setof.domain.core.product.ProductGroup;
import com.ryuqq.setof.domain.core.product.ProductGroupNameConfig;
import com.ryuqq.setof.domain.core.product.gpt.DescriptionResult;
import com.ryuqq.setof.domain.core.site.external.ExternalProductProcessingResultQueryService;
import com.ryuqq.setof.domain.core.site.external.ExternalProductProcessingResult;
import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaProductNameContext;
import com.ryuqq.setof.support.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class BuyMaProductDetailGenerator {

    private final ExternalProductProcessingResultQueryService externalProductProcessingResultQueryService;

    public BuyMaProductDetailGenerator(ExternalProductProcessingResultQueryService externalProductProcessingResultQueryService) {
        this.externalProductProcessingResultQueryService = externalProductProcessingResultQueryService;
    }

    public BuyMaProductNameContext generateBuyMaProductDetail(ProductGroup productGroup) {
        String description = productGroup.productGroupName();
        String name =productGroup.productGroupName();
        //getName(productGroup);

        if(containsKorean(description) || containsKorean(name)) {
            throw new IllegalArgumentException(String.format("Product Group Name Can't Contain Korean word %s, %s", name, description));
        }

        return new BuyMaProductNameContext(name, description);
    }

    private String getDescriptionOrDefault(ProductGroup productGroup) {
        return externalProductProcessingResultQueryService
                .findExternalProductProcessing(productGroup.productGroupId(), ProductDataType.DESCRIPTION)
                .map(this::extractDescription)
                .orElse(getName(productGroup));
    }

    private String extractDescription(ExternalProductProcessingResult result) {
        DescriptionResult descriptionResult = JsonUtils.fromJson(result.result(), DescriptionResult.class);
        return descriptionResult.description();
    }

    private String getName(ProductGroup productGroup) {
        return "";
//        return productGroup.config().getProductGroupNameConfigs().stream()
//                .filter(config -> config.countryCode().equals(Origin.US))
//                .map(ProductGroupNameConfig::customName)
//                .findFirst()
//                .orElse(productGroup.productGroupName());
    }

    private boolean containsKorean(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        return text.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*");
    }
}