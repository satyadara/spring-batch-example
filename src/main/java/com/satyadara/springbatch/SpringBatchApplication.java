package com.satyadara.springbatch;

import com.satyadara.springbatch.batch.jdbc.JdbcBatchJob;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatchApplication implements CommandLineRunner {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JdbcBatchJob jdbcBatchJob;

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        jobLauncher.run(jdbcBatchJob.job(), new JobParameters());
    }

}
