package com.satyadara.springbatch.batch.excel;

import com.satyadara.springbatch.batch.BatchConfig;
import com.satyadara.springbatch.batch.jpa.JpaWriter;
import com.satyadara.springbatch.dto.ExcelItemDTO;
import com.satyadara.springbatch.model.ItemSecondModel;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ExcelBatchJob implements BatchConfig<ExcelItemDTO, ItemSecondModel> {
    private static final int CHUNK_SIZE = 10;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ExcelReader excelReader;
    @Autowired
    private ExcelProcessor excelProcessor;
    @Autowired
    private JpaWriter jpaWriter;


    @Bean
    public SXSSFWorkbook workbook() {
        return new SXSSFWorkbook(CHUNK_SIZE);
    }

    @Override
    @Bean(name = "excelStep")
    public Step step() {
        return stepBuilderFactory.get("EXCEL_STEP")
                .<ExcelItemDTO, ItemSecondModel>chunk(CHUNK_SIZE)
                .reader(excelReader)
                .processor(excelProcessor)
                .writer(jpaWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Override
    @Bean(name = "excelJob")
    public Job job() {
        return jobBuilderFactory.get("EXCEL_JOB")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }
}
