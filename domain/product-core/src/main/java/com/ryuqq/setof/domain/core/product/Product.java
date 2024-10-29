package com.ryuqq.setof.domain.core.product;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class Product {

    private long productGroupId;
    private long productId;
    private int quantity;
    private boolean soldOutYn;
    private boolean displayYn;
    private String option;
    private Set<Option> options;
    private BigDecimal additionalPrice;


    public Product(long productGroupId, long productId, int quantity, boolean soldOutYn, boolean displayYn, String option, Set<Option> options, BigDecimal additionalPrice) {
        this.productGroupId = productGroupId;
        this.productId = productId;
        this.quantity = quantity;
        this.soldOutYn = soldOutYn;
        this.displayYn = displayYn;
        this.option = option;
        this.options = options;
        this.additionalPrice = additionalPrice;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isSoldOutYn() {
        return soldOutYn;
    }

    public boolean isDisplayYn() {
        return displayYn;
    }

    public String getOption() {
        return option;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public BigDecimal getAdditionalPrice() {
        return additionalPrice;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return productGroupId == product.productGroupId &&
                productId == product.productId &&
                quantity == product.quantity &&
                soldOutYn == product.soldOutYn &&
                displayYn == product.displayYn &&
                Objects.equals(option, product.option) &&
                Objects.equals(options, product.options) &&
                Objects.equals(additionalPrice, product.additionalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupId, productId, quantity, soldOutYn, displayYn, option, options, additionalPrice);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productGroupId=" + productGroupId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", soldOutYn=" + soldOutYn +
                ", displayYn=" + displayYn +
                ", option='" + option + '\'' +
                ", options=" + options +
                ", additionalPrice=" + additionalPrice +
                '}';
    }
}
