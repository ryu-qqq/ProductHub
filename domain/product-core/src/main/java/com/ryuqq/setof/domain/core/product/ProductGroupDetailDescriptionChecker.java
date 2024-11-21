package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductGroupDetailDescriptionChecker implements UpdateChecker<ProductDetailDescription, ProductDetailDescriptionCommand>{

    @Override
    public UpdateDecision checkUpdates(ProductDetailDescription existing, ProductDetailDescriptionCommand updated) {
        UpdateDecision decision = new UpdateDecision();

        if (!Objects.equals(existing.getDetailDescription(), updated.detailDescription())) {
            decision.addBatchUpdate(updated);
        }

        return decision;
    }

}
