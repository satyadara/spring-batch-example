package com.satyadara.springbatch.batch.excel;

import com.satyadara.springbatch.dto.ExcelItemDTO;
import com.satyadara.springbatch.model.ItemSecondModel;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ExcelProcessor implements ItemProcessor<ExcelItemDTO, ItemSecondModel> {
    @Override
    public ItemSecondModel process(ExcelItemDTO it) throws Exception {
        return ItemSecondModel.builder()
                .id(Long.valueOf(it.getId()))
                .name(it.getName())
                .quantity(Integer.parseInt(it.getQuantity()))
                .description(it.getDescription() + " Excel Move")
                .build();
    }
}
