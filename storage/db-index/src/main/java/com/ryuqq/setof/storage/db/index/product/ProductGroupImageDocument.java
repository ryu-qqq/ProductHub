package com.ryuqq.setof.storage.db.index.product;

import com.ryuqq.setof.enums.core.ProductImageType;

public record ProductGroupImageDocument(ProductImageType productImageType, String imageUrl, String originUrl) {
}
