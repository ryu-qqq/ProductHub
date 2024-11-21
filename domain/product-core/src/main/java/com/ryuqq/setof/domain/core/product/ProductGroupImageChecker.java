package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductGroupImageChecker implements UpdateChecker<List<ProductGroupImage>, List<ProductGroupImageCommand>>{

    @Override
    public UpdateDecision checkUpdates(List<ProductGroupImage> existing, List<ProductGroupImageCommand> updated) {
        UpdateDecision decision = new UpdateDecision();

        Map<String, ProductGroupImage> existingMap = existing.stream()
                .collect(Collectors.toMap(ProductGroupImage::getOriginUrl, Function.identity()));

        for (ProductGroupImageCommand c : updated) {
            ProductGroupImage ei = existingMap.get(c.imageUrl());

            if (ei != null) {
                if (ei.needsUpdate(c.imageUrl())) {
                    decision.addBatchUpdate(new ProductGroupImageCommand(ei.getProductGroupImageId(), c.productImageType(), c.imageUrl(), false));
                }
                existingMap.remove(c.imageUrl());
            } else {
                decision.addBatchUpdate(c);
            }
        }

        existingMap.values().forEach(
                p -> {
                    p.delete();
                    decision.addBatchUpdate(new ProductGroupImageCommand(p.getProductGroupImageId(), p.getProductImageType(), p.getImageUrl(), true));
                }
        );

        return decision;
    }


}
