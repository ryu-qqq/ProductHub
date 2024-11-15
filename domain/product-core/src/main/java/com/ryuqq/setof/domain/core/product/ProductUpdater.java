package com.ryuqq.setof.domain.core.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductUpdater {

    private final List<Product> productsToUpdate = new ArrayList<>();
    private final List<ProductCommand> optionUpdaters = new ArrayList<>();

    public ProductUpdater(Set<Product> existingProducts, List<ProductCommand> productCommands, long productGroupId) {
        Map<String, Product> existingProductMap = existingProducts.stream()
                .collect(Collectors.toMap(Product::getOption, Function.identity()));

        for (ProductCommand command : productCommands) {
            Product existingProduct = existingProductMap.get(command.getOption());

            if (existingProduct != null) {
                if (existingProduct.requiresUpdate(command)) {
                    productsToUpdate.add(existingProduct);
                }
                existingProductMap.remove(command.getOption());
            } else {
                optionUpdaters.add(command);
            }
        }

        existingProductMap.values().forEach(product -> {
            product.delete();
            productsToUpdate.add(product);
        });
    }


    public List<Product> getProductsToUpdate() {
        return productsToUpdate;
    }

    public List<ProductCommand> getOptionUpdaters() {
        return optionUpdaters;
    }
}