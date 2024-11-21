package com.ryuqq.setof.batch.core.product;

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
    private final ProductGroupBatchReader reader;
    private final ProductGroupBatchProcessor processor;
    private final ProductGroupBatchWriter writer;

    public ProductGroupRegistrationBatchConfig(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ProductGroupBatchReader reader,
            ProductGroupBatchProcessor processor,
            ProductGroupBatchWriter writer
    ) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.reader = reader;
        this.processor = processor;
        this.writer = writer;
    }

    @Bean
    public Job productGroupInsertJob() {
        return new JobBuilder("productGroupInsertJob", jobRepository)
                .start(productGroupInsertStep())
                .build();
    }

    @Bean
    public Step productGroupInsertStep() {
        return new StepBuilder("productGroupInsertStep", jobRepository)
                .<List<ProductGroupProcessingData>, ProductGroupBatchInsertData>chunk(2, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
