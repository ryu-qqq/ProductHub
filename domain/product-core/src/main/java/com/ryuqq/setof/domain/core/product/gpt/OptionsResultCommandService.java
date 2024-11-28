package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.domain.core.site.external.ExternalProductCommandService;
import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupPersistenceRepository;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProcessingResultPersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionsResultCommandService extends AbstractBatchResultCommandService<OptionsResult>{


    protected OptionsResultCommandService(ExternalProcessingResultPersistenceRepository externalProcessingResultPersistenceRepository, ExternalProductCommandService externalProductPersistenceService, ProductGroupPersistenceRepository productGroupPersistenceRepository) {
        super(externalProcessingResultPersistenceRepository, externalProductPersistenceService, productGroupPersistenceRepository);
    }

    @Override
    public void execute(OptionsResult optionsResult) {
        saveExternalProductResult(toEntity(optionsResult));
        updateProductGroupAndExternalProductReviewStatus(List.of(optionsResult));

    }

    @Override
    public void execute(List<OptionsResult> optionsResults) {
        saveExternalProductResults(toEntities(optionsResults));
        updateProductGroupAndExternalProductReviewStatus(optionsResults);

    }

    @Override
    public ProductDataType getProductDataType() {
        return ProductDataType.OPTIONS;
    }
}
