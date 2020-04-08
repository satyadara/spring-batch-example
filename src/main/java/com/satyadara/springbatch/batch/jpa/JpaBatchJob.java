package com.satyadara.springbatch.batch.jpa;

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
public class JpaBatchJob implements BatchConfig<ItemModel, ItemSecondModel> {
    private static final int CHUNK_SIZE = 10;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JpaReader jpaReader;
    @Autowired
    private JpaProcessor jpaProcessor;
    @Autowired
    private JpaWriter jpaWriter;

    @Override
    @Bean(name = "jpaStep")
    public Step step() {
        return stepBuilderFactory.get("JPA_STEP")
                .<ItemModel, ItemSecondModel>chunk(CHUNK_SIZE)
                .reader(jpaReader)
                .processor(jpaProcessor)
                .writer(jpaWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Override
    @Bean(name = "jpaJob")
    public Job job() {
        return jobBuilderFactory.get("JPA_JOB")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }
}
