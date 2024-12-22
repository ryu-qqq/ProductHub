package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.domain.core.site.external.ExternalProductGroupCommandService;
import com.ryuqq.setof.db.core.product.group.ProductGroupConfigPersistenceRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ExternalProductBatchWriter implements ItemWriter<ExternalProductBatchInsertData> {

    private final ExternalProductGroupCommandService externalProductPersistenceService;
    private final ProductGroupConfigPersistenceRepository productGroupConfigPersistenceRepository;

    public ExternalProductBatchWriter(ExternalProductGroupCommandService externalProductPersistenceService, ProductGroupConfigPersistenceRepository productGroupConfigPersistenceRepository) {
        this.externalProductPersistenceService = externalProductPersistenceService;
        this.productGroupConfigPersistenceRepository = productGroupConfigPersistenceRepository;
    }


    @Override
    public void write(Chunk<? extends ExternalProductBatchInsertData> items) {
        for (ExternalProductBatchInsertData data : items) {
            externalProductPersistenceService.saveAllExternalProductGroup(data.getExternalProductEntities());
            productGroupConfigPersistenceRepository.saveAllProductGroupNameConfigEntities(new ArrayList<>(data.getProductGroupNameConfigEntities()));
        }
    }

}
