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
    public Integer integration(long siteId, long sellerId, SyncStatus status, int pageSize) {
        ExternalSyncBatchContext syncBatchContext = fetchSyncBatchContext(siteId, sellerId, status, pageSize);

        SyncResultSummary syncResultSummary = executeSync(syncBatchContext);

        ExternalMallSyncResponseContext responseContext = processSyncResults(siteId, syncResultSummary);

        persistSyncResults(responseContext);

        return responseContext.externalProductUpdateCommands().size();
    }

    private ExternalSyncBatchContext fetchSyncBatchContext(long siteId, long sellerId, SyncStatus status, int pageSize) {
        return productPreExternalSyncContextFinder.fetchBySiteId(siteId, sellerId, status, pageSize);
    }

    private SyncResultSummary executeSync(ExternalSyncBatchContext syncBatchContext) {
        return externalMallSyncService.sync(
                externalMallSyncBatchContextAdapter.toExternalMallContexts(syncBatchContext)
        );
    }

    private ExternalMallSyncResponseContext processSyncResults(long siteId, SyncResultSummary syncResultSummary) {
        return externalMallSyncResponseContextAdapter.toDomains(siteId, syncResultSummary);
    }


    private void persistSyncResults(ExternalMallSyncResponseContext responseContext) {
        if (!responseContext.externalProductUpdateCommands().isEmpty()) {
            externalProductCommandService.updateExternalProduct(responseContext.externalProductUpdateCommands());
        }

        if (!responseContext.externalProductImageCommands().isEmpty()) {
            externalProductImageCommandService.saveAllExternalProductImages(responseContext.externalProductImageCommands());
        }

        if (!responseContext.externalRequestCommands().isEmpty()) {
            externalRequestCommandService.saveAllExternalRequest(responseContext.externalRequestCommands());
        }
    }

}
