package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.domain.core.site.external.ExternalProductCommandService;
import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupPersistenceRepository;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProcessingResultPersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescriptionResultCommandService extends AbstractBatchResultCommandService<DescriptionResult>{


    protected DescriptionResultCommandService(ExternalProcessingResultPersistenceRepository externalProcessingResultPersistenceRepository, ExternalProductCommandService externalProductPersistenceService, ProductGroupPersistenceRepository productGroupPersistenceRepository) {
        super(externalProcessingResultPersistenceRepository, externalProductPersistenceService, productGroupPersistenceRepository);
    }

    @Override
    public void execute(DescriptionResult descriptionResult) {
        saveExternalProductResult(toEntity(descriptionResult));
        updateProductGroupAndExternalProductReviewStatus(List.of(descriptionResult));
    }

    @Override
    public void execute(List<DescriptionResult> descriptionResults) {
        saveExternalProductResults(toEntities(descriptionResults));
        updateProductGroupAndExternalProductReviewStatus(descriptionResults);
    }

    @Override
    public ProductDataType getProductDataType() {
        return ProductDataType.DESCRIPTION;
    }
}
