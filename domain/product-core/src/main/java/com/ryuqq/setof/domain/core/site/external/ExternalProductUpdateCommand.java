package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProductEntity;

import java.math.BigDecimal;

public record ExternalProductUpdateCommand(
        long siteId,
        long productGroupId,
        String externalProductId,
        String productName,
        BigDecimal regularPrice,
        BigDecimal currentPrice,
        SyncStatus syncStatus,
        boolean soldOutYn,
        boolean displayYn
) {

    public ExternalProductEntity toEntity(){
        return new ExternalProductEntity(siteId, productGroupId, 0L,
                externalProductId, productName, regularPrice, currentPrice,
                syncStatus, soldOutYn, displayYn);
    }

}
