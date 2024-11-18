package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.option.ProductEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record ProductCommand(
        boolean soldOutYn,
        boolean displayYn,
        int quantity,
        BigDecimal additionalPrice,
        List<OptionCommand> options
) {

    public ProductEntity toEntity(long productGroupId){
        return new ProductEntity(productGroupId, soldOutYn, displayYn, quantity, additionalPrice);
    }

    public String getOption(){
        return options.stream()
                .map(OptionCommand::value)
                .collect(Collectors.joining(" "));
    }
}
