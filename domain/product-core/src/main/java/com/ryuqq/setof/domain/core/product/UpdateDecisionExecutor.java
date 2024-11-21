package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;

@Component
public class UpdateDecisionExecutor {

    private final ProductGroupUpdateHandlerProvider handlerProvider;

    public UpdateDecisionExecutor(ProductGroupUpdateHandlerProvider handlerProvider) {
        this.handlerProvider = handlerProvider;
    }

    public void execute(long productGroupId, UpdateDecision decision) {
        decision.getRealTimeUpdates().forEach(entity -> handleRealTimeUpdate(productGroupId, entity));
        decision.getBatchUpdates().forEach(entity -> handleUpdate(productGroupId, entity));
    }


    private <T> void handleUpdate(long productGroupId, UpdateDomain<T> entity) {
        @SuppressWarnings("unchecked")
        ProductGroupUpdateHandler<T> handler = (ProductGroupUpdateHandler<T>) handlerProvider.get(entity.getDomain().getClass());
        handler.handle(productGroupId, entity.getDomain());
    }


    //Todo 실시간 반영로직 넣어야함
    private <T> void handleRealTimeUpdate(long productGroupId, UpdateDomain<T> entity) {
        handleUpdate(productGroupId, entity);

    }

}
