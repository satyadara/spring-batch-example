package com.satyadara.springbatch.batch.jdbc;

import com.satyadara.springbatch.model.ItemSecondModel;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;

@Component
public class JdbcWriter extends JdbcBatchItemWriter<ItemSecondModel> {

    public JdbcWriter(DataSource dataSource) {
        this.setDataSource(dataSource);
        this.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
        this.setItemSqlParameterSourceProvider(
                it -> new MapSqlParameterSource(new HashMap<String, Object>() {
                    {
                        put("id", it.getId());
                        put("name", it.getName());
                        put("quantity", it.getQuantity());
                        put("description", it.getDescription());
                    }
                }));
        this.setSql("INSERT INTO item_second_table(id, name, quantity, description) VALUES (:id, :name, :quantity, :description)");
    }
}
