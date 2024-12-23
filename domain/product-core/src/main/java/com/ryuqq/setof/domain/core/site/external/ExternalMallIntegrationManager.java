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
    private final ExternalProductGroupCommandService externalProductGroupCommandService;
    private final ExternalRequestCommandService externalRequestCommandService;
    private final ExternalProductImageCommandService externalProductImageCommandService;

    public ExternalMallIntegrationManager(ProductPreExternalSyncContextFinder productPreExternalSyncContextFinder, ExternalMallSyncBatchContextAdapter externalMallSyncBatchContextAdapter, ExternalMallSyncResponseContextAdapter externalMallSyncResponseContextAdapter, ExternalMallSyncService externalMallSyncService, ExternalProductGroupCommandService externalProductGroupCommandService, ExternalRequestCommandService externalRequestCommandService, ExternalProductImageCommandService externalProductImageCommandService) {
        this.productPreExternalSyncContextFinder = productPreExternalSyncContextFinder;
        this.externalMallSyncBatchContextAdapter = externalMallSyncBatchContextAdapter;
        this.externalMallSyncResponseContextAdapter = externalMallSyncResponseContextAdapter;
        this.externalMallSyncService = externalMallSyncService;
        this.externalProductGroupCommandService = externalProductGroupCommandService;
        this.externalRequestCommandService = externalRequestCommandService;
        this.externalProductImageCommandService = externalProductImageCommandService;
    }

    @Transactional
    public Integer integration(long siteId, long sellerId, SyncStatus status, int pageSize) {
        ExternalSyncBatchContext syncBatchContext = fetchSyncBatchContext(siteId, sellerId, status, pageSize);

        SyncResultSummary syncResultSummary = executeSync(syncBatchContext);

        ExternalMallSyncResponseContext responseContext = processSyncResults(siteId, syncResultSummary);

        persistSyncResults(responseContext);

        return responseContext.externalProductGroupUpdateCommands().size();
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
        if (!responseContext.externalProductGroupUpdateCommands().isEmpty()) {
            externalProductGroupCommandService.updateExternalProductGroup(responseContext.externalProductGroupUpdateCommands());
        }

        if (!responseContext.externalProductImageCommands().isEmpty()) {
            externalProductImageCommandService.saveAllExternalProductImages(responseContext.externalProductImageCommands());
        }

        if (!responseContext.externalRequestCommands().isEmpty()) {
            externalRequestCommandService.saveAllExternalRequest(responseContext.externalRequestCommands());
        }
    }

}
