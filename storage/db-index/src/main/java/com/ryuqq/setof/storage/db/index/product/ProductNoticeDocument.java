package com.ryuqq.setof.storage.db.index.product;

import com.ryuqq.setof.enums.core.Origin;

public record ProductNoticeDocument(String material, String color, String size, String maker, Origin origin,
                                    String washingMethod, String yearMonth, String assuranceStandard, String asPhone) {
}

