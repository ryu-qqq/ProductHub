package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductChecker implements UpdateChecker<List<Product>, List<ProductCommand>>{

    @Override
    public UpdateDecision checkUpdates(List<Product> existing, List<ProductCommand> updated) {
        UpdateDecision updateDecision = new UpdateDecision();

        Map<String, Product> existingProductMap = existing.stream()
                .collect(Collectors.toMap(Product::getOption, Function.identity()));

        if(existingProductMap.isEmpty()){
            existing.stream().findFirst().ifPresent(product -> {
                product.delete();
                updateDecision.addRealTimeUpdate(toCommand(product));
            });
        }

        for (ProductCommand command : updated) {
            Product existingProduct = existingProductMap.get(command.getOption());

            if (existingProduct != null) {
                if (requiresUpdate(existingProduct, command)) {
                    updateDecision.addRealTimeUpdate(toCommand(existingProduct.getProductId(), command));
                }
                existingProductMap.remove(command.getOption());
            } else {
                updateDecision.addRealTimeUpdate(command);
            }
        }

        existingProductMap.values().forEach(product -> {
            product.delete();
            updateDecision.addRealTimeUpdate(toCommand(product));
        });

        return updateDecision;
    }

    public ProductCommand toCommand(long productId, ProductCommand command) {
        return new ProductCommand(productId,
                command.soldOutYn(), command.displayYn(),
                command.quantity(), command.additionalPrice(),
                command.option(),
                command.options(), false);
    }

    public ProductCommand toCommand(Product product) {
        return new ProductCommand(product.getProductId(),
                product.isSoldOutYn(), product.isDisplayYn(),
                product.getQuantity(), product.getAdditionalPrice().getAmount(),
                product.getOption(),
                List.of(), product.isDeleteYn());
    }


    private boolean requiresUpdate(Product existingProduct, ProductCommand command) {
        return existingProduct.isSoldOutYn() != command.soldOutYn() ||
                existingProduct.isDisplayYn() != command.displayYn() ||
                existingProduct.getQuantity() != command.quantity() ||
                !existingProduct.getAdditionalPrice().getAmount().equals(command.additionalPrice());

    }
}