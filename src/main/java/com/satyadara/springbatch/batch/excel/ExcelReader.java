package com.satyadara.springbatch.batch.excel;

import com.satyadara.springbatch.dto.ExcelItemDTO;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ExcelReader extends PoiItemReader<ExcelItemDTO> {
    public ExcelReader() {
        this.setLinesToSkip(1);
        this.setResource(new ClassPathResource("satyadara_item_table.xlsx"));
        this.setRowMapper(excelRowMapper());
    }

    private RowMapper<ExcelItemDTO> excelRowMapper() {
        BeanWrapperRowMapper<ExcelItemDTO> rowMapper = new BeanWrapperRowMapper<>();
        rowMapper.setTargetType(ExcelItemDTO.class);
        return rowMapper;
    }
}
