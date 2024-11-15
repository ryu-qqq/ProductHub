package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.option.ProductEntity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class Product {

    private final Long productGroupId;
    private final Long productId;
    private final int quantity;
    private final boolean soldOutYn;
    private final boolean displayYn;
    private final String option;
    private final Set<Option> options;
    private final BigDecimal additionalPrice;
    private boolean deleteYn;


    public Product(Long productGroupId, Long productId, int quantity, boolean soldOutYn, boolean displayYn, String option, Set<Option> options, BigDecimal additionalPrice) {
        this.productGroupId = productGroupId;
        this.productId = productId;
        this.quantity = quantity;
        this.soldOutYn = soldOutYn;
        this.displayYn = displayYn;
        this.option = option;
        this.options = options;
        this.additionalPrice = additionalPrice;
        this.deleteYn= false;
    }



    public Long getProductGroupId() {
        return productGroupId;
    }

    public Long getProductId() {
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

    public boolean isDeleteYn() {
        return deleteYn;
    }

    public void delete(){
        this.deleteYn = true;
    }

    public boolean requiresUpdate(ProductCommand command) {
        return this.isSoldOutYn() != command.soldOutYn() ||
                this.isDisplayYn() != command.displayYn() ||
                this.getQuantity() != command.quantity() ||
                !this.getAdditionalPrice().equals(command.additionalPrice());
    }


    public ProductEntity toEntity(){
        return new ProductEntity(productGroupId, soldOutYn, displayYn, quantity, additionalPrice);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return productGroupId.equals(product.productGroupId) &&
                productId.equals(product.productId) &&
                quantity == product.quantity &&
                soldOutYn == product.soldOutYn &&
                displayYn == product.displayYn &&
                Objects.equals(option, product.option) &&
                Objects.equals(options, product.options) &&
                Objects.equals(additionalPrice, product.additionalPrice) &&
                Objects.equals(deleteYn, product.deleteYn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupId, productId, quantity, soldOutYn, displayYn, option, options, additionalPrice, deleteYn);
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
                ", deleteYn=" + deleteYn +
                '}';
    }
}
