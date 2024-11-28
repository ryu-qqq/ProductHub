package com.ryuqq.setof.batch.core.brand;

import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.storage.db.index.brand.BrandDocument;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BrandIndexingBatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final BrandIndexingBatchReader reader;
    private final BrandIndexingBatchProcessor processor;
    private final BrandIndexingBatchWriter writer;

    public BrandIndexingBatchConfig(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            BrandIndexingBatchReader reader,
            BrandIndexingBatchProcessor processor,
            BrandIndexingBatchWriter writer
    ) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.reader = reader;
        this.processor = processor;
        this.writer = writer;
    }

    @Bean
    public Job brandIndexingJob() {
        return new JobBuilder("brandIndexingJob", jobRepository)
                .start(brandIndexingStep())
                .build();
    }

    @Bean
    public Step brandIndexingStep() {
        return new StepBuilder("brandIndexingStep", jobRepository)
                .<Brand, BrandDocument>chunk(100, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
