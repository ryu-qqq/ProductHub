package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductPreExternalSyncContextFinder {

    private final ExternalSyncBatchContextMapper externalSyncBatchContextMapper;
    private final ExternalProductQueryService externalProductQueryService;
    private final ProductPreExternalSyncAssembler productPreExternalSyncAssembler;

    public ProductPreExternalSyncContextFinder(ExternalSyncBatchContextMapper externalSyncBatchContextMapper, ExternalProductQueryService externalProductQueryService, ProductPreExternalSyncAssembler productPreExternalSyncAssembler) {
        this.externalSyncBatchContextMapper = externalSyncBatchContextMapper;
        this.externalProductQueryService = externalProductQueryService;
        this.productPreExternalSyncAssembler = productPreExternalSyncAssembler;
    }

    public ExternalSyncBatchContext fetchBySiteId(long siteId, long sellerId, SyncStatus status, int pageSize){
        List<ExternalProduct> externalProducts = externalProductQueryService.fetchByFilter(ExternalProductFilter.of(siteId, sellerId, status, pageSize));
        ProductPreExternalSyncAggregate productPreExternalSyncAggregate = productPreExternalSyncAssembler.assemble(siteId, externalProducts);
        return externalSyncBatchContextMapper.toDomain(productPreExternalSyncAggregate);
    }


}
