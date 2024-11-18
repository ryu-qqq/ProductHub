package com.ryuqq.setof.storage.db.index.product;

import java.math.BigDecimal;

public record ProductGroupCommandDocument(Long brandId, Long categoryId, Long sellerId, String productGroupName,
                                          String styleCode, String productCondition, String managementType,
                                          String optionType, BigDecimal regularPrice, BigDecimal currentPrice,
                                          boolean soldOutYn, boolean displayYn, String status, String keywords) {

}