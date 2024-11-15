package com.ryuqq.setof.domain.core.product;

import java.util.Map;

public class ProductOptionUpdater {

    private final ProductCommand productCommand;

    public ProductOptionUpdater(ProductCommand productCommand, Map<String, Option> existingOptionsMap) {
        this.productCommand = productCommand;
        processOptions(existingOptionsMap);
    }

    private void processOptions(Map<String, Option> existingOptionsMap) {
        for (OptionCommand option : productCommand.options()) {
            String optionKey = option.name() + ":" + option.value();
            existingOptionsMap.remove(optionKey);
        }
    }

    public ProductCommand getProductCommand() {
        return productCommand;
    }

}
