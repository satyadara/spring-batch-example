package com.satyadara.springbatch.batch.csv;

import com.satyadara.springbatch.model.ItemSecondModel;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CsvWriter extends FlatFileItemWriter<ItemSecondModel> {
    private static final String SEPARATOR = ",";

    public CsvWriter() {
        Resource resource = new FileSystemResource("/Users/satya/Documents/satyadara_item_table_2.csv");
        this.setResource(resource);
        this.setHeaderCallback(wr -> wr.write("id,name,quantity,description"));
        this.setLineAggregator(myLineAggregator());
    }

    private DelimitedLineAggregator<ItemSecondModel> myLineAggregator() {
        return new DelimitedLineAggregator<ItemSecondModel>() {
            {
                setDelimiter(SEPARATOR);
                setFieldExtractor(new BeanWrapperFieldExtractor<ItemSecondModel>() {
                    {
                        setNames(new String[]{"id", "name", "quantity", "description"});
                    }
                });
            }
        };
    }
}
