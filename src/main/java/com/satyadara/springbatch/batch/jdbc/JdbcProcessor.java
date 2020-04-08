package com.satyadara.springbatch.batch.jdbc;

import com.satyadara.springbatch.model.ItemModel;
import com.satyadara.springbatch.model.ItemSecondModel;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcProcessor implements ItemProcessor<ItemModel, ItemSecondModel> {
    @Override
    public ItemSecondModel process(ItemModel it) throws Exception {
        return ItemSecondModel.builder()
                .id(it.getId())
                .name(it.getName())
                .quantity(it.getQuantity())
                .description(it.getDescription() + " moved")
                .build();
    }
}
