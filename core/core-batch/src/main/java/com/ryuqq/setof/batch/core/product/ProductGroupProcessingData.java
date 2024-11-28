package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.storage.db.index.product.ProductGroupCommandContextDocument;

public record ProductGroupProcessingData(
        ProductGroupCommandContextDocument productGroupCommandContextDocument
) {}
