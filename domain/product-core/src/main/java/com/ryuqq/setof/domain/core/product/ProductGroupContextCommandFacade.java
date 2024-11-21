package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.index.product.ProductGroupDocumentIndexingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupContextCommandFacade implements ProductGroupContextCommandService {

    private final ProductGroupCommandService productGroupCommandService;
    private final ProductGroupQueryService productGroupQueryService;
    private final ProductGroupDocumentIndexingService productGroupDocumentIndexingService;
    private final ProductGroupContextUpdater productGroupContextUpdater;
    private final UpdateDecisionExecutor updateDecisionExecutor;

    public ProductGroupContextCommandFacade(ProductGroupCommandService productGroupCommandService, ProductGroupQueryService productGroupQueryService, ProductGroupDocumentIndexingService productGroupDocumentIndexingService, ProductGroupContextUpdater productGroupContextUpdater, UpdateDecisionExecutor updateDecisionExecutor) {
        this.productGroupCommandService = productGroupCommandService;
        this.productGroupQueryService = productGroupQueryService;
        this.productGroupDocumentIndexingService = productGroupDocumentIndexingService;
        this.productGroupContextUpdater = productGroupContextUpdater;
        this.updateDecisionExecutor = updateDecisionExecutor;
    }


    @Transactional
    public long insert(ProductGroupCommandContext context) {
        long productGroupId = productGroupCommandService.insert(context.productGroupCommand());
        productGroupDocumentIndexingService.insert(context.toDocument(productGroupId));
        return productGroupId;
    }

    @Transactional
    public long update(long productGroupId, ProductGroupCommandContext context) {
        ProductGroupContext existingContext = productGroupQueryService.findProductGroupContext(productGroupId);
        UpdateDecision decision = productGroupContextUpdater.checkUpdates(existingContext, context);
        updateDecisionExecutor.execute(productGroupId, decision);
        return productGroupId;
    }

}
