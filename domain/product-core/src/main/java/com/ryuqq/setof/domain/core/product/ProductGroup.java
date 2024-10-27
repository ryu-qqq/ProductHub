package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.core.ManagementType;
import com.ryuqq.setof.core.OptionType;
import com.ryuqq.setof.core.ProductCondition;
import com.ryuqq.setof.core.ProductStatus;

import java.math.BigDecimal;
import java.util.List;

public record ProductGroup(
        long brandId,
        long categoryId,
        long sellerId,
        String productGroupName,
        String styleCode,
        ProductCondition productCondition,
        ManagementType managementType,
        OptionType optionType,
        BigDecimal regularPrice,
        BigDecimal currentPrice,
        boolean soldOutYn,
        boolean displayYn,
        ProductStatus productStatus,
        ProductNotice productNotice,
        ProductDelivery productDelivery,
        List<ProductGroupImage> productGroupImages,
        ProductDetailDescription productDetailDescription,
        List<Product> skus
) {}
