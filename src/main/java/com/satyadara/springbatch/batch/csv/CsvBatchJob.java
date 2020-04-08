package com.satyadara.springbatch.batch.csv;

import com.satyadara.springbatch.batch.BatchConfig;
import com.satyadara.springbatch.model.ItemModel;
import com.satyadara.springbatch.model.ItemSecondModel;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CsvBatchJob implements BatchConfig<ItemModel, ItemSecondModel> {
    private static final int CHUNK_SIZE = 10;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private CsvReader csvReader;
    @Autowired
    private CsvProcessor csvProcessor;
    @Autowired
    private CsvWriter csvWriter;

    @Override
    @Bean(name = "csvStep")
    public Step step() {
        return stepBuilderFactory.get("CSV_STEP")
                .<ItemModel, ItemSecondModel>chunk(CHUNK_SIZE)
                .reader(csvReader)
                .processor(csvProcessor)
                .writer(csvWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Override
    @Bean(name = "csvJob")
    public Job job() {
        return jobBuilderFactory.get("CSV_JOB")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }
}
