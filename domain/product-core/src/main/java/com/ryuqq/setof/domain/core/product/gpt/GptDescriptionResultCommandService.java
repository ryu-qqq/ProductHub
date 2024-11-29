package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.storage.db.core.product.gpt.ProductProcessingResultPersistenceRepository;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupPersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GptDescriptionResultCommandService extends AbstractGptBatchResultCommandService<GptDescriptionResult> {


    protected GptDescriptionResultCommandService(ProductProcessingResultPersistenceRepository productProcessingResultPersistenceRepository, ProductGroupPersistenceRepository productGroupPersistenceRepository) {
        super(productProcessingResultPersistenceRepository, productGroupPersistenceRepository);
    }

    @Override
    public void execute(GptDescriptionResult descriptionResult) {
        saveExternalProductResult(toEntity(descriptionResult));
        updateProductGroupAndExternalProductReviewStatus(List.of(descriptionResult));
    }

    @Override
    public void execute(List<GptDescriptionResult> descriptionResults) {
        saveExternalProductResults(toEntities(descriptionResults));
        updateProductGroupAndExternalProductReviewStatus(descriptionResults);
    }

    @Override
    public ProductDataType getProductDataType() {
        return ProductDataType.DESCRIPTION;
    }
}
