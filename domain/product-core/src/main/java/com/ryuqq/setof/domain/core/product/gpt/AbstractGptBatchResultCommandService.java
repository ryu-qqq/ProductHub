package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.product.gpt.ProductProcessingResultPersistenceRepository;
import com.ryuqq.setof.storage.db.core.product.gpt.ProuctProcessingResultEntity;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupPersistenceRepository;
import com.ryuqq.setof.support.utils.JsonUtils;

import java.util.List;

public abstract class AbstractGptBatchResultCommandService<T extends GptBatchResult>implements GptBatchResultCommandService<T> {

    private final ProductProcessingResultPersistenceRepository productProcessingResultPersistenceRepository;
    private final ProductGroupPersistenceRepository productGroupPersistenceRepository;

    protected AbstractGptBatchResultCommandService(ProductProcessingResultPersistenceRepository productProcessingResultPersistenceRepository, ProductGroupPersistenceRepository productGroupPersistenceRepository) {
        this.productProcessingResultPersistenceRepository = productProcessingResultPersistenceRepository;
        this.productGroupPersistenceRepository = productGroupPersistenceRepository;
    }


    protected ProductGroupPersistenceRepository getProductGroupPersistenceService(){
        return productGroupPersistenceRepository;
    }

    protected void saveExternalProductResult(ProuctProcessingResultEntity processingResultEntity){
        saveExternalProductResults(List.of(processingResultEntity));
    }

    protected void saveExternalProductResults(List<ProuctProcessingResultEntity> processingResultEntities){
        productProcessingResultPersistenceRepository.saveAllExternalProcessingResult(processingResultEntities);
    }

    protected void updateProductGroupAndExternalProductReviewStatus(List<T> results){
        List<Long> productGroupIds = getProductGroupIds(results);
        productGroupPersistenceRepository.updatesProductStatus(productGroupIds, ProductStatus.REVIEW);
    }

    protected List<ProuctProcessingResultEntity> toEntities(List<T> results){
        return results.stream()
                .map(this::toEntity)
                .toList();
    }


    protected ProuctProcessingResultEntity toEntity(T result){
        return new ProuctProcessingResultEntity(
                result.getProductGroupId(),
                result.getProductDataType(),
                JsonUtils.toJson(result)
        );
    }


    protected List<Long> getProductGroupIds(List<T> results){
        return results.stream().map(GptBatchResult::getProductGroupId).toList();
    }

}
