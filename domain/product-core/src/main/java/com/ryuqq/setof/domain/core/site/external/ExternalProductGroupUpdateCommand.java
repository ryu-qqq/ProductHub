package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.db.core.site.external.ExternalProductGroupEntity;

import java.math.BigDecimal;

public record ExternalProductGroupUpdateCommand(
        long siteId,
        long productGroupId,
        String externalProductId,
        String productName,
        BigDecimal regularPrice,
        BigDecimal currentPrice,
        SyncStatus syncStatus,
        boolean fixedPriceYn,
        boolean soldOutYn,
        boolean displayYn
) {

    public ExternalProductGroupEntity toEntity(){
        return new ExternalProductGroupEntity(siteId, productGroupId, 0L,
                externalProductId, productName, regularPrice, currentPrice,
                syncStatus, fixedPriceYn, soldOutYn, displayYn);
    }

}
