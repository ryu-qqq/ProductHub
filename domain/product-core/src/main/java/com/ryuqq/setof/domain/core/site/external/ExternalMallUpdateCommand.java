package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.product.Price;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProductEntity;
import com.ryuqq.setof.support.external.core.ExternalMallImageResult;
import com.ryuqq.setof.support.external.core.ExternalMallUpdateResult;

import java.util.List;

public record ExternalMallUpdateCommand(
        long productGroupId,
        long siteId,
        String externalProductId,
        String productName,
        Price price,
        SyncStatus status,
        boolean soldOutYn,
        boolean displayYn,

        List<ExternalProductImageCommand> externalProductImageCommands
) {

    public static ExternalMallUpdateCommand of(ExternalMallUpdateResult externalMallUpdateResult){
        return new ExternalMallUpdateCommand(
                externalMallUpdateResult.productGroupId(),
                externalMallUpdateResult.siteId(),
                externalMallUpdateResult.externalProductId(),
                externalMallUpdateResult.productName(),
                new Price(externalMallUpdateResult.regularPrice(), externalMallUpdateResult.currentPrice()),
                externalMallUpdateResult.status(),
                externalMallUpdateResult.soldOutYn(), externalMallUpdateResult.displayYn(),
                externalMallUpdateResult.externalMallImageResults().stream().map(ExternalMallUpdateCommand::ImageCommandOf).toList()
        );
    }

    private static ExternalProductImageCommand ImageCommandOf(ExternalMallImageResult externalMallImageResult){
        return new ExternalProductImageCommand(
                externalMallImageResult.productGroupId(),
                externalMallImageResult.externalProductId(),
                externalMallImageResult.displayOrder(),
                externalMallImageResult.imageUrl(),
                externalMallImageResult.originUrl()
        );
    }

    public ExternalProductEntity toEntity(){
        return new ExternalProductEntity(siteId, productGroupId, 0L,
                externalProductId, productName, price.getRegularPrice().getAmount(), price.getCurrentPrice().getAmount(),
                status, soldOutYn, displayYn);
    }

}
