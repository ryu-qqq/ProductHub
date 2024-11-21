package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.domain.core.exception.ApplicationException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupBatchService {

    private final JobLauncher jobLauncher;
    private final Job productGroupInsertJob;

    public ProductGroupBatchService(JobLauncher jobLauncher, Job productGroupInsertJob) {
        this.jobLauncher = jobLauncher;
        this.productGroupInsertJob = productGroupInsertJob;
    }

    public String executeProductGroupInsertJob() {
        var jobParameters = new JobParametersBuilder()
                .addLong("startTime", System.currentTimeMillis())
                .toJobParameters();
        try {
            JobExecution jobExecution = jobLauncher.run(productGroupInsertJob, jobParameters);
            return "Batch job has been triggered. Execution ID: " + jobExecution.getId();
        } catch (Exception e) {
            throw new ApplicationException(500, e.getMessage());
        }
    }

}
