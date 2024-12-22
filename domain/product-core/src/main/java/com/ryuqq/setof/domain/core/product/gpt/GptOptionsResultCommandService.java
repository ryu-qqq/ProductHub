package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.db.core.product.gpt.ProductProcessingResultPersistenceRepository;
import com.ryuqq.setof.db.core.product.group.ProductGroupPersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GptOptionsResultCommandService extends AbstractGptBatchResultCommandService<GptOptionsResult> {


    protected GptOptionsResultCommandService(ProductProcessingResultPersistenceRepository productProcessingResultPersistenceRepository, ProductGroupPersistenceRepository productGroupPersistenceRepository) {
        super(productProcessingResultPersistenceRepository, productGroupPersistenceRepository);
    }

    @Override
    public void execute(GptOptionsResult optionsResult) {
        saveExternalProductResult(toEntity(optionsResult));
        updateProductGroupAndExternalProductReviewStatus(List.of(optionsResult));

    }

    @Override
    public void execute(List<GptOptionsResult> optionsResults) {
        saveExternalProductResults(toEntities(optionsResults));
        updateProductGroupAndExternalProductReviewStatus(optionsResults);

    }

    @Override
    public ProductDataType getProductDataType() {
        return ProductDataType.OPTIONS;
    }
}
