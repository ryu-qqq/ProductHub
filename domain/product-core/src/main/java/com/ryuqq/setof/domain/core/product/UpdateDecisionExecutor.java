package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.site.external.ExternalProductGroupCommandService;
import com.ryuqq.setof.enums.core.SyncStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateDecisionExecutor {

    private final ProductGroupUpdateHandlerProvider handlerProvider;
    private final ExternalProductGroupCommandService externalProductGroupCommandService;

    public UpdateDecisionExecutor(ProductGroupUpdateHandlerProvider handlerProvider, ExternalProductGroupCommandService externalProductGroupCommandService) {
        this.handlerProvider = handlerProvider;
        this.externalProductGroupCommandService = externalProductGroupCommandService;
    }

    public void execute(long productGroupId, UpdateDecision decision) {
        decision.getRealTimeUpdates().forEach(entity -> handleRealTimeUpdate(productGroupId, entity));
        decision.getBatchUpdates().forEach(entity -> handleUpdate(productGroupId, entity));
        externalProductGroupCommandService.updateSyncStatus(List.of(productGroupId), 4L, SyncStatus.SYNC_REQUIRED);
    }


    private <T> void handleUpdate(long productGroupId, UpdateDomain<T> entity) {
        @SuppressWarnings("unchecked")
        ProductGroupUpdateHandler<T> handler = (ProductGroupUpdateHandler<T>) handlerProvider.get(entity.getDomain().getClass());
        handler.handle(productGroupId, entity.getDomain());
    }


    private <T> void handleRealTimeUpdate(long productGroupId, UpdateDomain<T> entity) {
        handleUpdate(productGroupId, entity);
    }

}
