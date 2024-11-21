package com.ryuqq.setof.batch.core.brand;

import com.ryuqq.setof.domain.core.exception.ApplicationException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Service
public class BrandIndexingBatchService {

    private final JobLauncher jobLauncher;
    private final Job brandIndexingJob;

    public BrandIndexingBatchService(JobLauncher jobLauncher, Job brandIndexingJob) {
        this.jobLauncher = jobLauncher;
        this.brandIndexingJob = brandIndexingJob;
    }

    public String executeBrandIndexingJob() {
        var jobParameters = new JobParametersBuilder()
                .addLong("startTime", System.currentTimeMillis())
                .toJobParameters();
        try {
            JobExecution jobExecution = jobLauncher.run(brandIndexingJob, jobParameters);
            return "Batch job has been triggered. Execution ID: " + jobExecution.getId();
        } catch (Exception e) {
            throw new ApplicationException(500, e.getMessage());
        }
    }

}
