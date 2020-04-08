package com.satyadara.springbatch.batch.jdbc;

import com.satyadara.springbatch.model.ItemModel;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcReader extends JdbcCursorItemReader<ItemModel> {

    public JdbcReader(DataSource dataSource) {
        this.setDataSource(dataSource);
        this.setSql("SELECT id, name, quantity, description FROM item_table");
        this.setRowMapper(
                (rs, row) -> ItemModel.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .description(rs.getString("description"))
                        .quantity(rs.getInt("quantity"))
                        .build());
    }
}
