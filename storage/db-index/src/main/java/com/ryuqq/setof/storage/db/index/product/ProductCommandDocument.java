package com.ryuqq.setof.storage.db.index.product;

import java.math.BigDecimal;
import java.util.List;

public record ProductCommandDocument(boolean soldOutYn, boolean displayYn,
                                     int quantity, BigDecimal additionalPrice,
                                     List<OptionDocument> options)
{}

