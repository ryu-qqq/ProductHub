package com.ryuqq.setof.support.external.core;


import com.ryuqq.setof.enums.core.SiteName;

import java.util.Map;

public interface ExternalMallContextMapper {

    SiteName getSiteName();
    ExternalMallProductDetailContext generateProductDetailContext(ExternalMallPreProductContext externalMallPreProductContext);
    ExternalMallCategoryAndBrandContext generateCategoryAndBrand(ExternalMallPreProductContext externalMallPreProductContext);
    ExternalMallPriceContext generatePriceHolder(ExternalMallPreProductContext externalMallPreProductContext);

    ExternalMallOptionContext generateOptionContext(ExternalMallPreProductContext externalMallPreProductContext);
    ExternalMallImageGroupContext generateImageGroupContext(ExternalMallPreProductContext externalMallPreProductContext);

}
