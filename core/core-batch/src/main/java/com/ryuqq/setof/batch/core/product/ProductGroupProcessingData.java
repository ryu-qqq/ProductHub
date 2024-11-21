package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.storage.db.core.site.dto.SitePolicyDto;
import com.ryuqq.setof.storage.db.index.product.ProductGroupCommandContextDocument;

import java.util.List;

public record ProductGroupProcessingData(
        ProductGroupCommandContextDocument productGroupCommandContextDocument,
        List<SitePolicyDto> sitePolicies
) {}
