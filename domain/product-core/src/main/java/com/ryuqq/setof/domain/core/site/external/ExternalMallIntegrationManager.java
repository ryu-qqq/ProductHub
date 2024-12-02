package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.support.external.core.ExternalMallSyncService;
import com.ryuqq.setof.support.external.core.SyncResultSummary;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class ExternalMallIntegrationManager {

    private final ProductPreExternalSyncContextFinder productPreExternalSyncContextFinder;
    private final ExternalMallSyncBatchContextAdapter externalMallSyncBatchContextAdapter;
    private final ExternalMallSyncResponseContextAdapter externalMallSyncResponseContextAdapter;
    private final ExternalMallSyncService externalMallSyncService;
    private final ExternalProductCommandService externalProductCommandService;
    private final ExternalRequestCommandService externalRequestCommandService;
    private final ExternalProductImageCommandService externalProductImageCommandService;

    public ExternalMallIntegrationManager(ProductPreExternalSyncContextFinder productPreExternalSyncContextFinder, ExternalMallSyncBatchContextAdapter externalMallSyncBatchContextAdapter, ExternalMallSyncResponseContextAdapter externalMallSyncResponseContextAdapter, ExternalMallSyncService externalMallSyncService, ExternalProductCommandService externalProductCommandService, ExternalRequestCommandService externalRequestCommandService, ExternalProductImageCommandService externalProductImageCommandService) {
        this.productPreExternalSyncContextFinder = productPreExternalSyncContextFinder;
        this.externalMallSyncBatchContextAdapter = externalMallSyncBatchContextAdapter;
        this.externalMallSyncResponseContextAdapter = externalMallSyncResponseContextAdapter;
        this.externalMallSyncService = externalMallSyncService;
        this.externalProductCommandService = externalProductCommandService;
        this.externalRequestCommandService = externalRequestCommandService;
        this.externalProductImageCommandService = externalProductImageCommandService;
    }

    @Transactional
    public Integer integration(long siteId, long sellerId, SyncStatus status, int pageSize){
        ExternalSyncBatchContext externalSyncBatchContext = productPreExternalSyncContextFinder.fetchBySiteId(siteId, sellerId, status, pageSize);

        SyncResultSummary syncResultSummary = externalMallSyncService.sync(
                externalMallSyncBatchContextAdapter.toExternalMallContexts(externalSyncBatchContext)
        );

        ExternalMallSyncResponseContext domains = externalMallSyncResponseContextAdapter.toDomains(siteId, syncResultSummary);

        if(!domains.externalProductUpdateCommands().isEmpty()){
            externalProductCommandService.updateExternalProduct(domains.externalProductUpdateCommands());
        }

        if(!domains.externalProductImageCommands().isEmpty()){
            externalProductImageCommandService.saveAllExternalProductImages(domains.externalProductImageCommands());
        }

        if(!domains.externalRequestCommands().isEmpty()){
            externalRequestCommandService.saveAllExternalRequest(domains.externalRequestCommands());
        }

        return domains.externalProductUpdateCommands().size();
    }

}
