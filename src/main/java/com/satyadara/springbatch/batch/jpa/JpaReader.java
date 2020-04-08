package com.satyadara.springbatch.batch.jpa;

import com.satyadara.springbatch.model.ItemModel;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class JpaReader extends JpaPagingItemReader<ItemModel> {
    public JpaReader(EntityManagerFactory entityManagerFactory) {
        this.setEntityManagerFactory(entityManagerFactory);
        this.setQueryString("SELECT it FROM ItemModel it");
    }
}
