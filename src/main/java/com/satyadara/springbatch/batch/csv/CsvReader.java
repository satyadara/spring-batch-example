package com.satyadara.springbatch.batch.csv;

import com.satyadara.springbatch.model.ItemModel;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CsvReader extends FlatFileItemReader<ItemModel> {

    public CsvReader() {
        Resource resource = new ClassPathResource("satyadara_item_table.csv");
        this.setResource(resource);
        this.setLineMapper(myLineMapper());
    }

    private DefaultLineMapper<ItemModel> myLineMapper() {
        return new DefaultLineMapper<ItemModel>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames("id", "description", "name", "quantity");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<ItemModel>() {
                    {
                        setTargetType(ItemModel.class);
                    }
                });
            }
        };
    }
}
