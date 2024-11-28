package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.domain.core.site.external.ExternalProductCommandService;
import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupPersistenceRepository;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProcessingResultEntity;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProcessingResultPersistenceRepository;
import com.ryuqq.setof.support.utils.JsonUtils;

import java.util.List;

public abstract class AbstractBatchResultCommandService <T extends BatchResult>implements BatchResultCommandService<T> {

    private final ExternalProcessingResultPersistenceRepository externalProcessingResultPersistenceRepository;
    private final ExternalProductCommandService externalProductPersistenceService;
    private final ProductGroupPersistenceRepository productGroupPersistenceRepository;

    protected AbstractBatchResultCommandService(ExternalProcessingResultPersistenceRepository externalProcessingResultPersistenceRepository, ExternalProductCommandService externalProductPersistenceService, ProductGroupPersistenceRepository productGroupPersistenceRepository) {
        this.externalProcessingResultPersistenceRepository = externalProcessingResultPersistenceRepository;
        this.externalProductPersistenceService = externalProductPersistenceService;
        this.productGroupPersistenceRepository = productGroupPersistenceRepository;
    }

    protected ProductGroupPersistenceRepository getProductGroupPersistenceService(){
        return productGroupPersistenceRepository;
    }

    protected void saveExternalProductResult(ExternalProcessingResultEntity processingResultEntity){
        saveExternalProductResults(List.of(processingResultEntity));
    }

    protected void saveExternalProductResults(List<ExternalProcessingResultEntity> processingResultEntities){
        externalProcessingResultPersistenceRepository.saveAllExternalProcessingResult(processingResultEntities);
    }

    protected void updateProductGroupAndExternalProductReviewStatus(List<T> results){
        List<Long> productGroupIds = getProductGroupIds(results);
        productGroupPersistenceRepository.updatesProductStatus(productGroupIds, ProductStatus.REVIEW);
        externalProductPersistenceService.updateStatusByProductGroupId(productGroupIds, SyncStatus.REVIEW);
    }

    protected List<ExternalProcessingResultEntity> toEntities(List<T> results){
        return results.stream()
                .map(this::toEntity)
                .toList();
    }


    protected ExternalProcessingResultEntity toEntity(T result){
        return new ExternalProcessingResultEntity(
                result.getProductGroupId(),
                result.getProductDataType(),
                JsonUtils.toJson(result)
        );
    }


    protected List<Long> getProductGroupIds(List<T> results){
        return results.stream().map(BatchResult::getProductGroupId).toList();
    }

}
