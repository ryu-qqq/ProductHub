package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.domain.core.product.ProductGroupInsertRequest;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class ProductGroupRegistrationBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ProductGroupBatchReader productGroupInsertReader;
    private final ProductGroupBatchProcessor productGroupInsertProcessor;
    private final ProductGroupBatchWriterWithContext productGroupInsertWriter;
    private final ExternalProductBatchReader externalProductBatchReader;
    private final ExternalProductBatchProcessor externalProductBatchProcessor;
    private final ExternalProductBatchWriter externalProductBatchWriter;

    public ProductGroupRegistrationBatchConfig(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ProductGroupBatchReader productGroupInsertReader,
            ProductGroupBatchProcessor productGroupInsertProcessor,
            ProductGroupBatchWriterWithContext productGroupInsertWriter, ExternalProductBatchReader externalProductBatchReader, ExternalProductBatchProcessor externalProductBatchProcessor, ExternalProductBatchWriter externalProductBatchWriter
    ) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.productGroupInsertReader = productGroupInsertReader;
        this.productGroupInsertProcessor = productGroupInsertProcessor;
        this.productGroupInsertWriter = productGroupInsertWriter;
        this.externalProductBatchReader = externalProductBatchReader;
        this.externalProductBatchProcessor = externalProductBatchProcessor;
        this.externalProductBatchWriter = externalProductBatchWriter;
    }

    @Bean
    public Job productGroupInsertJob() {
        return new JobBuilder("productGroupInsertJob", jobRepository)
                .start(productGroupInsertStep())
                .next(externalProductInsertStep())
                .build();
    }

    @Bean
    public Step productGroupInsertStep() {
        return new StepBuilder("productGroupInsertStep", jobRepository)
                .<List<ProductGroupInsertRequest>, ProductGroupBatchInsertData>chunk(5, transactionManager)
                .reader(productGroupInsertReader)
                .processor(productGroupInsertProcessor)
                .writer(productGroupInsertWriter)
                .build();
    }

    @Bean
    public Step externalProductInsertStep() {
        return new StepBuilder("externalProductInsertStep", jobRepository)
                .<List<ExternalProductProcessingData>, ExternalProductBatchInsertData>chunk(5, transactionManager)
                .reader(externalProductBatchReader)
                .processor(externalProductBatchProcessor)
                .writer(externalProductBatchWriter)
                .build();
    }

}
