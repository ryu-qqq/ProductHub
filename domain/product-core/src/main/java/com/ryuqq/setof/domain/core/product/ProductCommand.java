package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.db.core.product.option.ProductEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record ProductCommand(
        Long id,
        boolean soldOutYn,
        boolean displayYn,
        int quantity,
        BigDecimal additionalPrice,
        String option,
        List<OptionCommand> options,
        boolean deleteYn
) {

    public ProductEntity toEntity(long productGroupId){
        if(id == null) return new ProductEntity(productGroupId, soldOutYn, displayYn, quantity, additionalPrice);
        else return  new ProductEntity(id, productGroupId, soldOutYn, displayYn, quantity, additionalPrice, deleteYn);
    }

    public String getOption(){
        return options.stream()
                .map(OptionCommand::value)
                .collect(Collectors.joining(" "));
    }
}
