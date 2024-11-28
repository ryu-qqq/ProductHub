package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.ManagementType;
import com.ryuqq.setof.enums.core.OptionType;
import com.ryuqq.setof.enums.core.ProductCondition;
import com.ryuqq.setof.enums.core.ProductStatus;

import java.util.List;

public record ProductGroup(
        long productGroupId, long setOfProductGroupId, long sellerId, long configId,
        List<Long> colorIds, Long categoryId, Long brandId, String productGroupName,
        String styleCode, ProductCondition productCondition, ManagementType managementType,
        OptionType optionType, Price price, boolean soldOutYn, boolean displayYn,
        ProductStatus productStatus, String keywords,
        ProductDelivery delivery, ProductNotice notice,
        List<ProductGroupImage> images, ProductDetailDescription detailDescription
) {}









