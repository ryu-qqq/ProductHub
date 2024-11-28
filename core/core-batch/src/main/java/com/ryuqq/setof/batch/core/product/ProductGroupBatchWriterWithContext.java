package com.ryuqq.setof.batch.core.product;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ProductGroupBatchWriterWithContext implements ItemWriter<ProductGroupBatchInsertData> {

    private final ProductGroupBatchWriter delegate;

    public ProductGroupBatchWriterWithContext(ProductGroupBatchWriter delegate) {
        this.delegate = delegate;
    }

    @Override
    public void write(Chunk<? extends ProductGroupBatchInsertData> items) throws Exception {
        delegate.write(items);

        List<Long> productGroupIds = items.getItems().stream()
                .flatMap(p -> p.productGroupIds().stream())
                .toList();

        List<Long> sellerIds = items.getItems().stream()
                .flatMap(p -> p.sellerIds().stream())
                .toList();

        StepExecution stepExecution = Objects.requireNonNull(StepSynchronizationManager.getContext()).getStepExecution();
        stepExecution.getJobExecution().getExecutionContext().put("productGroupIds", productGroupIds);
        stepExecution.getJobExecution().getExecutionContext().put("sellerIds", sellerIds);
    }
}
