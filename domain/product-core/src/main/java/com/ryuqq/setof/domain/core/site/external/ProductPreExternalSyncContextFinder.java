package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductPreExternalSyncContextFinder {

    private final ExternalSyncBatchContextMapper externalSyncBatchContextMapper;
    private final ExternalProductGroupQueryService externalProductGroupQueryService;
    private final ProductPreExternalSyncAssembler productPreExternalSyncAssembler;

    public ProductPreExternalSyncContextFinder(ExternalSyncBatchContextMapper externalSyncBatchContextMapper, ExternalProductGroupQueryService externalProductGroupQueryService, ProductPreExternalSyncAssembler productPreExternalSyncAssembler) {
        this.externalSyncBatchContextMapper = externalSyncBatchContextMapper;
        this.externalProductGroupQueryService = externalProductGroupQueryService;
        this.productPreExternalSyncAssembler = productPreExternalSyncAssembler;
    }

    public ExternalSyncBatchContext fetchBySiteId(long siteId, long sellerId, SyncStatus status, int pageSize){
        List<ExternalProductGroup> externalProductGroups = externalProductGroupQueryService.fetchByFilter(ExternalProductGroupFilter.of(siteId, sellerId, status, pageSize));
        ProductPreExternalSyncAggregate productPreExternalSyncAggregate = productPreExternalSyncAssembler.assemble(siteId, externalProductGroups);
        return externalSyncBatchContextMapper.toDomain(productPreExternalSyncAggregate);
    }


}
