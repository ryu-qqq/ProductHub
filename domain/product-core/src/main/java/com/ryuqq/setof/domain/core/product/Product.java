package com.ryuqq.setof.domain.core.product;

import java.math.BigDecimal;
import java.util.Set;

public class Product {

    private long productGroupId;
    private long productId;
    private int quantity;
    private boolean soldOutYn;
    private boolean displayYn;
    private String option;
    private Set<OptionContext> options;
    private BigDecimal additionalPrice;


}
