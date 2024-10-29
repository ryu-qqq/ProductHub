package com.ryuqq.setof.domain.core.product;


import java.util.List;
import java.util.Set;

public record ProductGroupContextRecord(
        ProductGroup productGroup,
        ProductDelivery delivery,
        ProductNotice notice,
        List<ProductGroupImage> images,
        ProductDetailDescription detailDescription,
        Set<Product> products
) {}
