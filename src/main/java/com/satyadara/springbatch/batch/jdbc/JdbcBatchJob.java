package com.satyadara.springbatch.batch.jdbc;

import com.satyadara.springbatch.model.ItemModel;
import com.satyadara.springbatch.model.ItemSecondModel;
import com.satyadara.springbatch.batch.BatchConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcBatchJob implements BatchConfig<ItemModel, ItemSecondModel> {
    private static final int CHUNK_SIZE = 10;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JdbcReader jdbcReader;
    @Autowired
    private JdbcProcessor jdbcProcessor;
    @Autowired
    private JdbcWriter jdbcWriter;

    @Override
    @Bean(name = "jdbcStep")
    public Step step() {
        return stepBuilderFactory.get("JDBC_STEP")
                .<ItemModel, ItemSecondModel>chunk(CHUNK_SIZE)
                .reader(jdbcReader)
                .processor(jdbcProcessor)
                .writer(jdbcWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Override
    @Bean(name = "jdbcJob")
    public Job job() {
        return jobBuilderFactory.get("JDBC_JOB")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }
}
