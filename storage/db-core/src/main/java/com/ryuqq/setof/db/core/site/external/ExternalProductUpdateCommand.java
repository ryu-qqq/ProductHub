package com.ryuqq.setof.storage.db.core.site.external;

import java.math.BigDecimal;

public record ExternalProductUpdateCommand(
        String externalProductGroupId,
        String externalProductId,
        String optionValue,
        int quantity,
        BigDecimal additionalPrice,
        boolean soldOutYn,
        boolean displayYn
) {

    public ExternalProductEntity toEntity(){
        return new ExternalProductEntity(
                externalProductGroupId,
                externalProductId,
                optionValue,
                quantity,
                additionalPrice,
                soldOutYn,
                displayYn
        );
    }
}
