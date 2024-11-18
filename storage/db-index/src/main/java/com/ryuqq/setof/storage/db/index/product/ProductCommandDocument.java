package com.ryuqq.setof.storage.db.index.product;

import java.math.BigDecimal;
import java.util.List;

public class ProductCommandDocument {
    private boolean soldOutYn;
    private boolean displayYn;
    private int quantity;
    private BigDecimal additionalPrice;
    private List<OptionDocument> options;

    public ProductCommandDocument(boolean soldOutYn, boolean displayYn, int quantity, BigDecimal additionalPrice, List<OptionDocument> options) {
        this.soldOutYn = soldOutYn;
        this.displayYn = displayYn;
        this.quantity = quantity;
        this.additionalPrice = additionalPrice;
        this.options = options;
    }
}

