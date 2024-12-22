package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.exception.NotFoundException;
import com.ryuqq.setof.db.core.product.group.ProductGroupInsertRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupContextCommandManager implements ProductGroupContextCommandService {

    private final ProductGroupCommandService productGroupCommandService;
    private final ProductGroupContextQueryService productGroupContextQueryService;
    private final ProductGroupInsertRequestRepository productGroupInsertRequestRepository;
    private final ProductGroupContextUpdater productGroupContextUpdater;
    private final UpdateDecisionExecutor updateDecisionExecutor;

    public ProductGroupContextCommandManager(ProductGroupCommandService productGroupCommandService, ProductGroupContextQueryService productGroupContextQueryService, ProductGroupInsertRequestRepository productGroupInsertRequestRepository, ProductGroupContextUpdater productGroupContextUpdater, UpdateDecisionExecutor updateDecisionExecutor) {
        this.productGroupCommandService = productGroupCommandService;
        this.productGroupContextQueryService = productGroupContextQueryService;
        this.productGroupInsertRequestRepository = productGroupInsertRequestRepository;
        this.productGroupContextUpdater = productGroupContextUpdater;
        this.updateDecisionExecutor = updateDecisionExecutor;
    }


    @Transactional
    public long insert(ProductGroupCommandContext context) {
        try{
            long productGroupId = productGroupCommandService.insert(context.productGroupCommand());
            productGroupInsertRequestRepository.save(context.toEntity(productGroupId));
            return productGroupId;
        }catch (Exception e){
            throw new IllegalArgumentException(e);
        }
    }

    @Transactional
    public long update(long productGroupId, ProductGroupCommandContext context) {
        try{
            ProductGroupContext existingContext = productGroupContextQueryService.fetchProductGroupContextById(productGroupId);
            UpdateDecision decision = productGroupContextUpdater.checkUpdates(existingContext, context);
            updateDecisionExecutor.execute(existingContext.getProductGroup().productGroupId(), decision);
            return productGroupId;
        }catch (NotFoundException e){
            return insert(context);
        }
    }

}
